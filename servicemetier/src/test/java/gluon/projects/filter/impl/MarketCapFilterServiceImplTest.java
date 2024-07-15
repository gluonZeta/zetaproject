package gluon.projects.filter.impl;

import gluon.projects.filter.MarketCapFilterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MarketCapFilterServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCapitalisationFilteredCrypto() {
        MarketCapFilterService marketCapFilterService = new MarketCapFilterServiceImpl();
        Map<String, Long> cryptoCapitalisation =  marketCapFilterService.getCapitalisationFilteredCrypto(true);
        assertNotNull(cryptoCapitalisation);
        assertFalse(cryptoCapitalisation.isEmpty());
    }
}