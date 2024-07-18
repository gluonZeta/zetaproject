package gluon.projects.socketbinance.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderBookData {

    private String symbol;

    private List<OrderBookElementData> bids;

    private List<OrderBookElementData> asks;

    public OrderBookData(String symbol) {
        this.symbol = symbol;
    }

    private OrderBookData() {}

    public void constructBidsAsks(String responsData) {
        JSONObject responseData = new JSONObject(responsData);
        JSONArray arrayBids = (JSONArray) responseData.get("bids");
        JSONArray arrayAsks = (JSONArray) responseData.get("asks");

        this.bids = new ArrayList<>();
        this.asks = new ArrayList<>();

        JSONArray element;
        OrderBookElementData orderBookElementData;
        float price;
        float qtt;
        for(int i = 0; i < arrayBids.length(); i++) {
            element = arrayBids.getJSONArray(i);
            price = Float.parseFloat((String) element.get(0));
            qtt = Float.parseFloat((String) element.get(1));
            orderBookElementData = new OrderBookElementData(price, qtt);
            this.bids.add(orderBookElementData);
        }
        for(int i = 0; i < arrayAsks.length(); i++) {
            element = arrayAsks.getJSONArray(i);
            price = Float.parseFloat((String) element.get(0));
            qtt = Float.parseFloat((String) element.get(1));
            orderBookElementData = new OrderBookElementData(price, qtt);
            this.asks.add(orderBookElementData);
        }
    }

    public void showValues() {
        StringBuilder stringBuilder = new StringBuilder();
        for(OrderBookElementData orderBookElementData: asks) {
            stringBuilder.append(orderBookElementData.toString()).append("\n");
        }

        stringBuilder.append("\n");

        for(OrderBookElementData orderBookElementData: bids) {
            stringBuilder.append(orderBookElementData.toString()).append("\n");
        }
        stringBuilder.append("\n----------------------------------------------------------------------------------");
        System.out.println(stringBuilder.toString());
    }

    public String getSize() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Bids: ").append(this.bids.size())
                .append(" # Asks: ").append(this.asks.size());
        return stringBuilder.toString();
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<OrderBookElementData> getBids() {
        return bids;
    }

    public void setBids(List<OrderBookElementData> bids) {
        this.bids = bids;
    }

    public List<OrderBookElementData> getAsks() {
        return asks;
    }

    public void setAsks(List<OrderBookElementData> asks) {
        this.asks = asks;
    }
}
