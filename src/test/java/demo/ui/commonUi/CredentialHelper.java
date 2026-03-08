package demo.ui.commonUi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CredentialHelper {
    public static JsonNode credentials;

    private CredentialHelper() {
        // Private constructor to prevent instantiation
    }

    static {
        try (InputStream inputStream = CredentialHelper.class.getClassLoader().getResourceAsStream("credential.json")) {
            if (inputStream == null) throw new RuntimeException("credential.json not found in resources");
            credentials = new ObjectMapper().readTree(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load credentials", e);
        }
    }

    // Helper method to get user credentials
    public static String getUsername(String userType) {
        return credentials.get("users").get(userType).get("username").asText();
    }

    public static String getPassword(String userType) {
        return credentials.get("users").get(userType).get("password").asText();
    }
}
