package demo.restapi.typicode.api;

public class ApiEndpoints {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String USERS_ENDPOINT = "/users";

    private ApiEndpoints() {
        // Private constructor to prevent instantiation
    }

    public static String getUsersUrl() {
        return BASE_URL + USERS_ENDPOINT;
    }
}
