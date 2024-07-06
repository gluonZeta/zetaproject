package gluon.projects.apibinance.impl;

import gluon.projects.apibinance.ApiBinanceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiBinanceServiceImplTest {

    private ApiBinanceService apiBinanceService;

    @BeforeEach
    void setUp() {
        this.apiBinanceService = new ApiBinanceServiceImpl();
    }

    @AfterEach
    void tearDown() {
        this.apiBinanceService = null;
        Runtime.getRuntime().gc();
    }

    @Test
    void apiBinancePing() {
        assertNotNull(this.apiBinanceService.apiBinancePing());
        assertEquals("{}", this.apiBinanceService.apiBinancePing());
    }

    @Test
    void getExchangeInformations() {
        assertNotNull(this.apiBinanceService.getExchangeInformations("BTCUSDT"));
        System.out.println(this.apiBinanceService.getExchangeInformations("BTCUSDT"));
    }
}