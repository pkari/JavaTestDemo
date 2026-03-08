package demo.restapi.typicode.api;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class UsersApi {
    private static final Logger logger = LogManager.getLogger(UsersApi.class);
    private final CloseableHttpClient httpClient;

    public UsersApi() {
        this.httpClient = HttpClients.createDefault();
    }

//    public UsersApi(CloseableHttpClient httpClient) {
//        this.httpClient = httpClient;
//    }

    public CloseableHttpResponse getUsers() throws IOException {
        logger.info("Sending GET request to: {}", ApiEndpoints.getUsersUrl());
        HttpGet request = new HttpGet(ApiEndpoints.getUsersUrl());
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
