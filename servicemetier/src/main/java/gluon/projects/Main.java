package gluon.projects;

import gluon.projects.symbol.SymbolService;
import gluon.projects.symbol.impl.SymbolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SymbolService symbolService = new SymbolServiceImpl();
        List<String> symbols = symbolService.getSymbolList();

        Random rand = new Random();
        String symbolToAnalyse = symbols.get(rand.nextInt(symbols.size()));
        logger.atInfo().log("############################### " + symbolToAnalyse);
        logger.atInfo().log(symbolService.getSymbolInformations(symbolToAnalyse));
    }
}
