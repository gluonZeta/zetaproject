package gluon.projects;

import gluon.projects.filter.MarketCapFilterService;
import gluon.projects.filter.impl.MarketCapFilterServiceImpl;
import gluon.projects.socketbinance.BinanceWebsocket;
import gluon.projects.socketbinance.OrderBookWebsocketService;
import gluon.projects.socketbinance.TradeWebsocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MarketCapFilterService marketCapFilterService = new MarketCapFilterServiceImpl();
        Map<String, Long> filteredCryptoSymbol = marketCapFilterService.getCapitalisationFilteredCrypto(false);
        List<String> cryptoSymbol = new ArrayList<>();

        List<String> symbolExclus = new ArrayList<>();
        symbolExclus.add("BTCUSDT");
        symbolExclus.add("ETHUSDT");
        symbolExclus.add("BNBUSDT");
        symbolExclus.add("USDCUSDT");
        symbolExclus.add("SOLUSDT");
        symbolExclus.add("TUSDUSDT");
        symbolExclus.add("FDUSDUSDT");
        symbolExclus.add("XRPUSDT");
        symbolExclus.add("RUNEUSDT");



        for(Map.Entry<String, Long> entry: filteredCryptoSymbol.entrySet()) {
            if(!symbolExclus.contains(entry.getKey()) && !entry.getKey().startsWith("USDT")){
                cryptoSymbol.add(entry.getKey());
            }
        }


        /*
        TradeWebsocketService tradeWebsocketService = new TradeWebsocketService(cryptoSymbol);
        //tradeWebsocketService.websocketConnect();
        tradeWebsocketService.websocketConnectIndividual("AXLUSDT");
         */
        OrderBookWebsocketService orderBookWebsocketService = new OrderBookWebsocketService(cryptoSymbol);
        orderBookWebsocketService.websocketConnectIndividual("AXLUSDT");





    }
}
