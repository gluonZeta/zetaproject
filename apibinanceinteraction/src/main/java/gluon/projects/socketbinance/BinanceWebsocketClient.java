package gluon.projects.socketbinance;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class BinanceWebsocketClient extends WebSocketClient {

    public BinanceWebsocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connected to Binance WebSocket server");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("Received message: " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("Connection closed: " + s);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
