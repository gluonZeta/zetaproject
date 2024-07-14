package gluon.projects;

import gluon.projects.filter.MarketCapFilterService;
import gluon.projects.filter.impl.MarketCapFilterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MarketCapFilterService marketCapFilterService = new MarketCapFilterServiceImpl();
        Map<String, Long> filteredCryptoSymbol = marketCapFilterService.getCapitalisationFilteredCrypto();
        for(Map.Entry<String, Long> entry: filteredCryptoSymbol.entrySet()) {
            logger.atInfo().log(entry.getKey() + " -- " + entry.getValue());
        }
    }
}
