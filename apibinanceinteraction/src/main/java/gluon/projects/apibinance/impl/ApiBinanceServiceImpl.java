package gluon.projects.apibinance.impl;

import gluon.projects.apibinance.ApiBinanceService;
import gluon.projects.utilities.PropertiesGetter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ApiBinanceServiceImpl implements ApiBinanceService {

    private Properties properties;

    private String apiBinanceUrl;

    private HttpRequest httpRequest;

    private HttpClient httpClient;

    private HttpResponse httpResponse;

    private String apiVersion = "/api/v3";

    public ApiBinanceServiceImpl() {
        this.properties = PropertiesGetter.getProperties("application.properties");
        this.apiBinanceUrl = this.properties.getProperty("apibinanceurl");
    }

    @Override
    public String apiBinancePing() {
        String apiBinancePingUrl = this.apiBinanceUrl + apiVersion + "/ping";
        return this.sendSimpleApiRequest(apiBinancePingUrl);
    }

    @Override
    public String getExchangeInformations(String symbol) {
        StringBuilder exchangeInformationsUrl = new StringBuilder(this.apiBinanceUrl);
        exchangeInformationsUrl.append(this.apiVersion);
        exchangeInformationsUrl.append("/exchangeInfo");
        exchangeInformationsUrl.append("?symbol=" + symbol);
        return this.sendSimpleApiRequest(exchangeInformationsUrl.toString());
    }


    private String sendSimpleApiRequest(String completeUrl) {
        this.httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(completeUrl))
                .build();
        this.httpClient = HttpClient.newHttpClient();

        try {
            this.httpResponse = this.httpClient.send(this.httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return (String) this.httpResponse.body();
    }


}
