package gluon.projects.apicoingecko.impl;

import gluon.projects.apicoingecko.ApiCoingeckoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApiCoingeckoServiceImplTest {

    ApiCoingeckoService apiCoingeckoService;

    @BeforeEach
    void setUp() {
        this.apiCoingeckoService = new ApiCoingeckoServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMarketCap() {
        Map<String, String> cryptoCapitalisation = this.apiCoingeckoService.getMarketCap();
        assertNotNull(cryptoCapitalisation);
        assertTrue(cryptoCapitalisation.size() > 10);
    }
}