package gluon.projects.filter.impl;

import gluon.projects.apicoingecko.ApiCoingeckoService;
import gluon.projects.apicoingecko.impl.ApiCoingeckoServiceImpl;
import gluon.projects.exceptions.MyFileNotFoundException;
import gluon.projects.exceptions.MyIOException;
import gluon.projects.filter.MarketCapFilterService;
import gluon.projects.symbol.SymbolService;
import gluon.projects.symbol.impl.SymbolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketCapFilterServiceImpl implements MarketCapFilterService {

    Logger logger = LoggerFactory.getLogger(MarketCapFilterServiceImpl.class);

    private static final long MINIMUM_CAPITALISATION = 1000000L;

    private ApiCoingeckoService apiCoingeckoService;

    private SymbolService symbolService;

    private static final String FILE_NAME = "SymbolWithCapitalisation.csv";

    public MarketCapFilterServiceImpl() {
        this.apiCoingeckoService = new ApiCoingeckoServiceImpl();
        this.symbolService = new SymbolServiceImpl();
    }

    @Override
    public Map<String, Long> getCapitalisationFilteredCrypto(boolean getFreshData) {
        Map<String, Long> filteredSymbol;

        if(getFreshData) {
            filteredSymbol = this.getNewData();
            logger.atInfo().log("Number of crypto to analyse : " + filteredSymbol.size());
            this.saveSymbolWithCapitalisation(filteredSymbol);

        } else {
            filteredSymbol = readSymbolWithCapitalisation();
        }

        return filteredSymbol;
    }

    private Map<String, Long> getNewData() {
        Map<String, Long> filteredSymbol = new HashMap<>();
        List<String> apiBinanceCrypto = this.symbolService.getSymbolList();
        Map<String, String> cryptoCapitalisation = this.apiCoingeckoService.getMarketCap();
        String cryptoSymbolTemp;
        int cryptoSymbolLength;
        long cryptoCapitaliation;

        for (String s : apiBinanceCrypto) {
            cryptoSymbolLength = s.length();
            cryptoSymbolTemp = s.toLowerCase().substring(0, cryptoSymbolLength - 4);
            for (Map.Entry<String, String> entry : cryptoCapitalisation.entrySet()) {
                if (cryptoSymbolTemp.equals(entry.getKey())) {
                    cryptoCapitaliation = Long.parseLong(entry.getValue());
                    if (cryptoCapitaliation > MINIMUM_CAPITALISATION) {
                        filteredSymbol.put(s, cryptoCapitaliation);
                    }
                }
            }
        }
        return filteredSymbol;
    }

    private void saveSymbolWithCapitalisation(Map<String, Long> filteredSymbol) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for(Map.Entry<String, Long> mapEntry: filteredSymbol.entrySet()) {
                bufferedWriter.write(mapEntry.getKey() + ";" + mapEntry.getValue());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new MyIOException(e);
        }
    }

    private Map<String, Long> readSymbolWithCapitalisation() {
        Map<String, Long> cryptoSymbolWithCapitalisation = new HashMap<>();
        String line;
        String[] symbolCapitalisation;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            while ((line = bufferedReader.readLine()) != null) {
                symbolCapitalisation = line.split(";");
                if(symbolCapitalisation.length >= 2) {
                    cryptoSymbolWithCapitalisation.put(symbolCapitalisation[0].trim(), Long.parseLong(symbolCapitalisation[1].trim()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new MyFileNotFoundException(e);
        } catch (IOException e) {
            throw new MyIOException(e);
        }

        return cryptoSymbolWithCapitalisation;
    }
}
