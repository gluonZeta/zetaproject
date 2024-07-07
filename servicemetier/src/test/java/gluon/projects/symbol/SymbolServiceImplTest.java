package gluon.projects.symbol;

import gluon.projects.symbol.impl.SymbolServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SymbolServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSymbolList() {
        SymbolServiceImpl servieSymbol = new SymbolServiceImpl();
        List<String> symbols =servieSymbol.getSymbolList();
        Assertions.assertTrue(symbols.size() > 10);
    }
}