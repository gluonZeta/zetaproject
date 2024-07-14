package gluon.projects.filter.impl;

import gluon.projects.apicoingecko.ApiCoingeckoService;
import gluon.projects.apicoingecko.impl.ApiCoingeckoServiceImpl;
import gluon.projects.filter.MarketCapFilterService;
import gluon.projects.symbol.SymbolService;
import gluon.projects.symbol.impl.SymbolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketCapFilterServiceImpl implements MarketCapFilterService {

    Logger logger = LoggerFactory.getLogger(MarketCapFilterServiceImpl.class);

    private static final long MINIMUM_CAPITALISATION = 1000000L;

    private ApiCoingeckoService apiCoingeckoService;

    private SymbolService symbolService;

    public MarketCapFilterServiceImpl() {
        this.apiCoingeckoService = new ApiCoingeckoServiceImpl();
        this.symbolService = new SymbolServiceImpl();
    }

    @Override
    public Map<String, Long> getCapitalisationFilteredCrypto() {
        List<String> apiBinanceCrypto = this.symbolService.getSymbolList();
        Map<String, String> cryptoCapitalisation = this.apiCoingeckoService.getMarketCap();
        String cryptoSymbolTemp;
        int cryptoSymbolLength;
        long cryptoCapitaliation;
        Map<String, Long> filteredSymbol = new HashMap<>();

        for(int i = 0; i < apiBinanceCrypto.size(); i++) {
            cryptoSymbolLength = apiBinanceCrypto.get(i).length();
            cryptoSymbolTemp = apiBinanceCrypto.get(i).toLowerCase().substring(0, cryptoSymbolLength - 4);
            for(Map.Entry<String, String> entry: cryptoCapitalisation.entrySet()) {
                if(cryptoSymbolTemp.equals(entry.getKey())) {
                    cryptoCapitaliation = Long.parseLong(entry.getValue());
                    if(cryptoCapitaliation > MINIMUM_CAPITALISATION) {
                        filteredSymbol.put(apiBinanceCrypto.get(i), cryptoCapitaliation);
                    }
                }
            }
        }

        logger.atInfo().log("Number of crypto to analyse : " + filteredSymbol.size());

        return filteredSymbol;
    }
}
