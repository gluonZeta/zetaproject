package gluon.projects.symbol;

import gluon.projects.symbol.impl.SymbolServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SymbolServiceImplTest {

    SymbolServiceImpl servieSymbol;

    @BeforeEach
    void setUp() {
        this.servieSymbol = new SymbolServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSymbolList() {
        List<String> symbols = this.servieSymbol.getSymbolList();
        assertTrue(symbols.size() > 10);
    }

    @Test
    void getSymbolInformations() {
        String symbolInformation = this.servieSymbol.getSymbolInformations("XRPUSDT");
        assertNotNull(symbolInformation);
        assertNotEquals("", symbolInformation);
    }
}