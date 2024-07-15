package gluon.projects.apibinance.impl;

import gluon.projects.apibinance.ApiBinanceService;
import gluon.projects.utilities.ApiRequest;
import gluon.projects.utilities.PropertiesGetter;

import java.util.Properties;

public class ApiBinanceServiceImpl implements ApiBinanceService {

    private Properties properties;

    private String apiBinanceUrl;

    private String apiVersion = "/api/v3";

    public ApiBinanceServiceImpl() {
        this.properties = PropertiesGetter.getProperties("application.properties");
        this.apiBinanceUrl = this.properties.getProperty("apibinanceurl");
    }

    @Override
    public String apiBinancePing() {
        String apiBinancePingUrl = this.apiBinanceUrl + apiVersion + "/ping";
        return ApiRequest.sendSimpleApiRequest(apiBinancePingUrl);
    }

    @Override
    public String getExchangeInformations(String symbol) {
        StringBuilder exchangeInformationsUrl = new StringBuilder(this.apiBinanceUrl);
        exchangeInformationsUrl.append(this.apiVersion);
        exchangeInformationsUrl.append("/exchangeInfo");
        exchangeInformationsUrl.append("?symbol=" + symbol);
        return ApiRequest.sendSimpleApiRequest(exchangeInformationsUrl.toString());
    }

    @Override
    public String getExchangeInformations() {
        StringBuilder exchangeInformationsUrl = new StringBuilder(this.apiBinanceUrl);
        exchangeInformationsUrl.append(this.apiVersion);
        exchangeInformationsUrl.append("/exchangeInfo");
        return ApiRequest.sendSimpleApiRequest(exchangeInformationsUrl.toString());
    }


}
