package gluon.projects.socketbinance.websocketclient;

import gluon.projects.socketbinance.data.OrderBookData;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class OrderBookWebsocketClient extends WebSocketClient {

    private static Logger logger = LoggerFactory.getLogger(OrderBookWebsocketClient.class);

    private OrderBookData orderBookData;

    public OrderBookWebsocketClient(URI serverUri, OrderBookData orderBookData) {
        super(serverUri);
        this.orderBookData = orderBookData;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.atInfo().log(this.orderBookData.getSymbol() + " ORDER BOOK Connected to Binance WebSocket server");
    }

    @Override
    public void onMessage(String s) {
        this.orderBookData.constructBidsAsks(s);
        this.orderBookData.showValues();
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.atInfo().log(this.orderBookData.getSymbol() + " ORDER BOOK Connection closed: " + s);
    }

    @Override
    public void onError(Exception e) {
        logger.atInfo().log(e.getMessage());
    }
}
