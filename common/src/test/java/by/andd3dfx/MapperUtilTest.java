package by.andd3dfx;

import by.andd3dfx.dto.SomeObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MapperUtilTest {

    @Test
    public void jsonToObject() {
        final String jsonString = "{ \"name\": \"Andrei\", \"nickname\": \"L@Rs\" }";

        SomeObject result = MapperUtil.jsonToObject(jsonString, SomeObject.class);

        assertThat(result.getName(), is("Andrei"));
        assertThat(result.getNickname(), is("L@Rs"));
    }

    @Test
    public void toJson() {
        final SomeObject object = SomeObject.builder()
                .name("Andrei")
                .nickname("L@Rs")
                .build();

        String jsonString = MapperUtil.toJson(object);

        assertThat(jsonString, is("{\"name\":\"Andrei\",\"nickname\":\"L@Rs\"}"));
    }
}
