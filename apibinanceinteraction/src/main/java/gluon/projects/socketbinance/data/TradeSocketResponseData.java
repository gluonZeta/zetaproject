package gluon.projects.socketbinance.data;

import java.util.Date;

public class TradeSocketResponseData {

    private String symbol;

    private boolean isSellerAttack;

    private float quantity;

    private float price;

    private Date tradingDate;


    public TradeSocketResponseData(String symbol){
        this.symbol = symbol;
    }

    public TradeSocketResponseData(){}

    public TradeSocketResponseData(boolean isSellerAttack, float quantity, float price, long longTradingTime) {
        this.isSellerAttack = isSellerAttack;
        this.quantity = quantity;
        this.price = price;
        this.tradingDate = new Date(longTradingTime);
    }

    public void setContent(boolean isSellerAttack,float quantity,float price, long longTradingTime) {
        this.isSellerAttack = isSellerAttack;
        this.quantity = quantity;
        this.price = price;
        this.tradingDate = new Date(longTradingTime);
    }

    public float getTotalPrice() {
        return quantity*price;
    }

    public boolean isSellerAttack() {
        return isSellerAttack;
    }

    public void setSellerAttack(boolean sellerAttack) {
        isSellerAttack = sellerAttack;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
