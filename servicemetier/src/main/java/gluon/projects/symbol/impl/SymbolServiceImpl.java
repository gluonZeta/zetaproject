package gluon.projects.symbol.impl;

import gluon.projects.apibinance.ApiBinanceService;
import gluon.projects.apibinance.impl.ApiBinanceServiceImpl;
import gluon.projects.symbol.SymbolService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SymbolServiceImpl implements SymbolService {

    private ApiBinanceService apiBinanceService;

    @Override
    public List<String> getSymbolList() {
        List<String> symbolList = new ArrayList<>();
        this.apiBinanceService = new ApiBinanceServiceImpl();

        String exchangeInformation = this.apiBinanceService.getExchangeInformations();
        JSONObject jsonObject = new JSONObject(exchangeInformation);
        JSONObject exchangeInfoSymbol;
        JSONArray symbols = (JSONArray) jsonObject.get("symbols");
        for(int i = 0; i < symbols.length(); i++) {
            exchangeInfoSymbol = new JSONObject(symbols.get(i).toString());
            symbolList.add((String) exchangeInfoSymbol.get("symbol"));
        }

        symbolList = symbolList
                .stream()
                .filter(s -> (s.contains("USDT")
                        && !s.contains("DOWN")
                        && !s.contains("BULL")
                        && !s.contains("BEAR")
                ))
                .collect(Collectors.toList());
        return symbolList;
    }
}
