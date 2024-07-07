package gluon.projects;

import gluon.projects.socketbinance.BinanceWebsocket;

public class Main {

    public static void main(String[] args) {
        BinanceWebsocket binanceWebsocket = new BinanceWebsocket();
        binanceWebsocket.websocketConnect();
    }

}
