package gluon.projects.socketbinance.data;

public class OrderBookElementData {

    private float price;

    private float qtt;

    private float total;

    public OrderBookElementData() {}

    public OrderBookElementData(float price, float qtt) {
        this.price = price;
        this.qtt = qtt;
        this.total = this.price * this.qtt;
    }

    public void setContent(float price, float qtt) {
        this.price = price;
        this.qtt = qtt;
        this.total = this.price * this.qtt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQtt() {
        return qtt;
    }

    public void setQtt(float qtt) {
        this.qtt = qtt;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", qtt=" + qtt +
                ", total=" + total +
                '}';
    }
}
