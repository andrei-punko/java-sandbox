package by.andd3dfx.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class MapperUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static <T> T jsonToObject(String jsonString, Class<T> aClass) {
        return objectMapper.readValue(jsonString, aClass);
    }

    @SneakyThrows
    public static String toJson(Object msa) {
        return objectMapper.writeValueAsString(msa);
    }
}
