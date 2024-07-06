package gluon.projects.utilities;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesGetterTest {

    @Test
    void getProperties() {
        Properties properties = PropertiesGetter.getProperties("application.properties");
        assertNotNull(properties);
        assertNotNull(properties.getProperty("apibinanceurl"));
        assertEquals("https://api.binance.com",properties.getProperty("apibinanceurl"));
    }
}