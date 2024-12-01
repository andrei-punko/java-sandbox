package by.andd3dfx.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class MapperUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Deserialize object from JSON string
     *
     * @param jsonString JSON string
     * @param aClass     object class
     * @param <T>        type of object class
     * @return deserialized object of class type T
     */
    @SneakyThrows
    public static <T> T jsonToObject(String jsonString, Class<T> aClass) {
        return objectMapper.readValue(jsonString, aClass);
    }

    /**
     * Serialize object to string
     *
     * @param object object for serialization
     * @return string with serialized object
     */
    @SneakyThrows
    public static String toJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
