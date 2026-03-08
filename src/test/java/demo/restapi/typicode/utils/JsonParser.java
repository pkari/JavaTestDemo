package demo.restapi.typicode.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JsonParser {
    private static final Logger logger = LogManager.getLogger(JsonParser.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonParser() {
        // Private constructor to prevent instantiation
    }

    public static <T> List<T> parseJsonArray(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            logger.error("Failed to parse JSON array: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("Failed to parse JSON: {}", e.getMessage());
            return null;
        }
    }

    public static <T> List<T> parseJsonArray(String json, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("Failed to parse JSON array: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
