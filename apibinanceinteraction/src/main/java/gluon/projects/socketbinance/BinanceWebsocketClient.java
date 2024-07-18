package gluon.projects.socketbinance;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class BinanceWebsocketClient extends WebSocketClient {

    private static Logger logger = LoggerFactory.getLogger(BinanceWebsocketClient.class);

    private String cryptoSymbol;

    public BinanceWebsocketClient(URI serverUri, String cryptoToAnalyse) {
        super(serverUri);
        this.cryptoSymbol = cryptoToAnalyse.toUpperCase();
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.atInfo().log("Connected to Binance WebSocket server");
    }

    @Override
    public void onMessage(String s) {
        logger.atInfo().log("Received Data: " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.atInfo().log("Connection closed: " + s);
    }

    @Override
    public void onError(Exception e) {
        logger.atInfo().log(e.getMessage());
    }
}
