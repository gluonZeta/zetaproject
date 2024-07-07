package gluon.projects.apibinance;

public interface ApiBinanceService {

    public String apiBinancePing();

    public String getExchangeInformations(String symbol);

    public String getExchangeInformations();

}
