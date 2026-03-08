package demo.restapi.typicode.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.BaseTest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import demo.restapi.typicode.api.UsersApi;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("api")
public class UsersApiResponseCodeTest extends BaseTest {
    private UsersApi usersApi;

    @BeforeEach
    public void setUp() {
        usersApi = new UsersApi();
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (usersApi != null) {
            usersApi.close();
        }
    }

    //Case 5
    @Test
    public void testGetUsersReturnsStatusCode200() throws IOException {
        try (CloseableHttpResponse response = usersApi.getUsers()) {
            logger.info("Test Starting: GET /users");
            assertEquals(200, response.getCode(), "Expected status code 200");

            // Parse response to JSON format
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(entity.getContent());

                for (JsonNode user : json) {
                    String name = user.get("name").asText();
                    String email = user.get("email").asText();
                    logger.info("User: " + name + ", Email: " + email);
                }

                if (!json.isEmpty()) {
                    String firstEmail = json.get(0).get("email").asText();
                    assertTrue(firstEmail.contains("@"), "The first email should contain '@'.");
                }
            } else {
                System.out.println("No users found in the response.");
            }
        }
    }
}
