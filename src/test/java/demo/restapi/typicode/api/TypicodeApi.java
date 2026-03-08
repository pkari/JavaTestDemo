package demo.restapi.typicode.api;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TypicodeApi {
    private static final Logger logger = LogManager.getLogger(TypicodeApi.class);
    private final CloseableHttpClient httpClient;

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String USERS_ENDPOINT = "/users";

    public static String getUsersUrl() {
        return BASE_URL + USERS_ENDPOINT;
    }

    public TypicodeApi() {
        this.httpClient = HttpClients.createDefault();
    }

    public CloseableHttpResponse getUsers() throws IOException {
        logger.info("Sending GET request to: {}", TypicodeApi.getUsersUrl());
        HttpGet request = new HttpGet(TypicodeApi.getUsersUrl());
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.executeOpen(null, request, null);
        logger.info("Received response with status code: {}", response.getCode());
        return response;
    }

    public void close() throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
