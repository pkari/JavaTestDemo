package demo.restapi.typicode.tests;

import demo.BaseTest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import demo.restapi.typicode.api.UsersApi;
import demo.restapi.typicode.models.User;
import demo.restapi.typicode.utils.JsonParser;

import java.io.IOException;
import java.util.List;

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
    public void testGetUsersReturnsStatusCode200() throws IOException, ParseException {
        try (CloseableHttpResponse response = usersApi.getUsers()) {
            logger.info("Test Starting: GET /users");
            assertEquals(200, response.getCode(), "Expected status code 200");

            // Parse response to JSON format
            String responseBody = EntityUtils.toString(response.getEntity());
            List<User> users = JsonParser.parseJsonArray(responseBody, User.class);

            assertNotNull(users, "Users list should not be null");
            assertFalse(users.isEmpty(), "Users list should not be empty");

            // Log only the names and emails from the response data
            logger.info("User Names and Emails:");
            for (User user : users) {
                logger.info("{} | {}", user.getName(), user.getEmail());
            }

            // Verify the first email address contains @
            String firstEmail = users.get(0).getEmail();
            assertTrue(firstEmail.contains("@"),
                String.format("First email '%s' should contain '@'", firstEmail));
        }
    }
}
