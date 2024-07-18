package gluon.projects.socketbinance;

import gluon.projects.myexception.ApiBinanceException;
import gluon.projects.socketbinance.data.OrderBookData;
import gluon.projects.socketbinance.data.OrderBookElementData;
import gluon.projects.socketbinance.data.TradeSocketResponseData;
import gluon.projects.socketbinance.websocketclient.OrderBookWebsocketClient;
import gluon.projects.socketbinance.websocketclient.TradeWebsocketClient;
import gluon.projects.utilities.PropertiesGetter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderBookWebsocketService {

    private Properties properties;

    private String socketOrderBookUrl;

    private List<String> symbols;

    public OrderBookWebsocketService(List<String> symbols) {
        this.properties = PropertiesGetter.getProperties("application.properties");
        this.socketOrderBookUrl = this.properties.getProperty("streambinancesocket");
        this.symbols = symbols;
    }

    public OrderBookWebsocketService() {}

    public void websocketConnectIndividual(String symbol) {
        String url;
        OrderBookWebsocketClient orderBookWebsocketClient;
        OrderBookData orderBookData = new OrderBookData(symbol);
        try {
            url = this.socketOrderBookUrl + "/" + symbol.toLowerCase() + "@depth20";
            orderBookWebsocketClient = new OrderBookWebsocketClient(new URI(url), orderBookData);
            orderBookWebsocketClient.connect();

        } catch (URISyntaxException e) {
            throw new ApiBinanceException(e);
        }
    }

}
