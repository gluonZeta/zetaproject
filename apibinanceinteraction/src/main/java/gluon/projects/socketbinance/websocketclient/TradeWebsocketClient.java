package gluon.projects.socketbinance.websocketclient;

import gluon.projects.socketbinance.data.TradeSocketResponseData;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.text.SimpleDateFormat;

public class TradeWebsocketClient extends WebSocketClient {

    private static final float TOTAL_THRESHOLD = 0F;

    private static Logger logger = LoggerFactory.getLogger(TradeWebsocketClient.class);

    private TradeSocketResponseData tradeSocketResponseData;

    public TradeWebsocketClient(URI serverUri, TradeSocketResponseData tradeSocketResponseData) {
        super(serverUri);
        this.tradeSocketResponseData = tradeSocketResponseData;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.atInfo().log(this.tradeSocketResponseData.getSymbol() + " Connected to Binance WebSocket server");
    }

    @Override
    public void onMessage(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        JSONObject responseData = new JSONObject(s);

        this.tradeSocketResponseData.setContent(
                (Boolean) responseData.get("m"),
                Float.parseFloat((String) responseData.get("q")),
                Float.parseFloat((String) responseData.get("p")),
                (long) responseData.get("E")
        );

        if(TOTAL_THRESHOLD < this.tradeSocketResponseData.getTotalPrice()) {
            StringBuilder myLog = new StringBuilder();

            myLog.append(this.tradeSocketResponseData.getSymbol() );
            myLog.append(" # Vendeur Attaque:").append(this.tradeSocketResponseData.isSellerAttack());
            myLog.append(" # TOTAL: ").append(this.tradeSocketResponseData.getTotalPrice());
            myLog.append(" # QTT: ").append(this.tradeSocketResponseData.getQuantity());
            myLog.append(" # PRICE: ").append(this.tradeSocketResponseData.getPrice());
            myLog.append(" # Trading time: ").append(sdf.format(this.tradeSocketResponseData.getTradingDate()));
            logger.atInfo().log(myLog.toString());
        }

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.atInfo().log(this.tradeSocketResponseData.getSymbol() + " Connection closed: " + s);
    }

    @Override
    public void onError(Exception e) {
        logger.atInfo().log(e.getMessage());
    }

}
