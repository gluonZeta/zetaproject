package gluon.projects.socketbinance;

import gluon.projects.myexception.ApiBinanceException;
import gluon.projects.socketbinance.data.TradeSocketResponseData;
import gluon.projects.socketbinance.websocketclient.TradeWebsocketClient;
import gluon.projects.utilities.PropertiesGetter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TradeWebsocketService {
    private Properties properties;

    private String socketBinanceUrl;

    private List<String> symbols;

    public TradeWebsocketService(List<String> symbols) {
        this.properties = PropertiesGetter.getProperties("application.properties");
        this.socketBinanceUrl = this.properties.getProperty("streambinancesocket");
        this.symbols = symbols;
    }

    public TradeWebsocketService() {}

    public void websocketConnect() {
        String url;
        List<TradeWebsocketClient> tradeWebsocketClients = new ArrayList<>();
        TradeWebsocketClient tradeWebsocketClient;
        TradeSocketResponseData tradeSocketResponseData;
        try {
            for(String s: this.symbols) {
                url = this.socketBinanceUrl + "/" + s.toLowerCase() + "@trade";
                tradeSocketResponseData = new TradeSocketResponseData(s);
                tradeWebsocketClient = new TradeWebsocketClient(new URI(url), tradeSocketResponseData);
                tradeWebsocketClient.setConnectionLostTimeout(20);
                tradeWebsocketClients.add(tradeWebsocketClient);
                tradeWebsocketClient.connect();
            }

        } catch (URISyntaxException e) {
            throw new ApiBinanceException(e);
        }
    }

    public void websocketConnectIndividual(String symbol) {
        String url;
        TradeWebsocketClient tradeWebsocketClient;
        TradeSocketResponseData tradeSocketResponseData;
        try {
            url = this.socketBinanceUrl + "/" + symbol.toLowerCase() + "@trade";
            tradeSocketResponseData = new TradeSocketResponseData(symbol);
            tradeWebsocketClient = new TradeWebsocketClient(new URI(url), tradeSocketResponseData);
            tradeWebsocketClient.setConnectionLostTimeout(20);
            tradeWebsocketClient.connect();

        } catch (URISyntaxException e) {
            throw new ApiBinanceException(e);
        }
    }
}
