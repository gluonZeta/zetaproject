package gluon.projects.utilities;

import gluon.projects.myexception.ApiBinanceException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {

    private ApiRequest() {
        throw new IllegalStateException("Utility class");
    }

    public static String sendSimpleApiRequest(String completeUrl) {
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
