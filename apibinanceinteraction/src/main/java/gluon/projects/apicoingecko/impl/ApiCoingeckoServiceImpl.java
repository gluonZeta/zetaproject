package gluon.projects.apicoingecko.impl;

import gluon.projects.apicoingecko.ApiCoingeckoService;
import gluon.projects.utilities.ApiRequest;
import gluon.projects.utilities.PropertiesGetter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApiCoingeckoServiceImpl implements ApiCoingeckoService {

    Logger logger = LoggerFactory.getLogger(ApiCoingeckoServiceImpl.class);

    private Properties properties;

    private StringBuilder apiCoingeckoUrl;

    private static final int MAX_LOOP = 20000;

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
                jsonArrayResponse.putAll(new JSONArray(ApiRequest.sendSimpleApiRequest(this.apiCoingeckoUrl.toString())));
            } catch (JSONException jsonException) {
                break;
            }
            if(page < MAX_LOOP) page++;
        }

        JSONObject jsonObject;
        for(int i = 0; i < jsonArrayResponse.length(); i++) {
            jsonObject = (JSONObject) jsonArrayResponse.get(i);
            capitalisation.put((String) jsonObject.get("symbol"), jsonObject.get("market_cap").toString());
        }

        return capitalisation;
    }

}
