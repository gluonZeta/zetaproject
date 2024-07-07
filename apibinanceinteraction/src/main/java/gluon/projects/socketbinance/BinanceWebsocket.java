package gluon.projects.socketbinance;

import gluon.projects.utilities.PropertiesGetter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class BinanceWebsocket {

    private Properties properties;

    private String socketBinanceUrl;

    public BinanceWebsocket() {
        this.properties = PropertiesGetter.getProperties("application.properties");
        this.socketBinanceUrl = this.properties.getProperty("streambinancesocket");
    }

    public void websocketConnect() {
        //String url = this.socketBinanceUrl + "/btcusdt@trade";
        //String url = this.socketBinanceUrl + "/adausdt@aggTrade";
        //String url = this.socketBinanceUrl + "/enausdt@bookTicker";
        String url = this.socketBinanceUrl + "/enausdt@depth5";
        try {
            BinanceWebsocketClient binanceWebsocketClient = new BinanceWebsocketClient(new URI(url));
            binanceWebsocketClient.connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
