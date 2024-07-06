package gluon.projects.socketbinance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinanceWebsocketTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createInteraction() {
        BinanceWebsocket binanceWebsocket = new BinanceWebsocket();
        binanceWebsocket.createInteraction();
    }
}