package gluon.projects.apicoingecko.impl;

import gluon.projects.apicoingecko.ApiCoingeckoService;
import gluon.projects.myexception.ApiBinanceException;
import gluon.projects.utilities.PropertiesGetter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApiCoingeckoServiceImpl implements ApiCoingeckoService {

    Logger logger = LoggerFactory.getLogger(ApiCoingeckoServiceImpl.class);

    private Properties properties;

    private StringBuilder apiCoingeckoUrl;

    public ApiCoingeckoServiceImpl() {
        this.properties = PropertiesGetter.getProperties("application.properties");
        this.apiCoingeckoUrl = new StringBuilder(this.properties.getProperty("coingeckoUrl"));
    }

    @Override
    public Map<String, String> getMarketCap() {
        Map<String, String> capitalisation = new HashMap<>();
        int page = 0;
        JSONArray jsonArrayResponse = new JSONArray();

        while(true) {
            this.apiCoingeckoUrl.append("?vs_currency=usd&order=market_cap_desc&per_page=250&page=").append(page);
            try {
                jsonArrayResponse.putAll(new JSONArray(this.sendSimpleApiRequest(this.apiCoingeckoUrl.toString())));
            } catch (JSONException jsonException) {
                break;
            }
            page++;
        }

        JSONObject jsonObject;
        for(int i = 0; i < jsonArrayResponse.length(); i++) {
            jsonObject = (JSONObject) jsonArrayResponse.get(i);
            capitalisation.put((String) jsonObject.get("symbol"), jsonObject.get("market_cap").toString());
        }

        return capitalisation;
    }

    private String sendSimpleApiRequest(String completeUrl) {
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(completeUrl))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse;

        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new ApiBinanceException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiBinanceException(e);
        }
        return httpResponse.body();
    }

}
