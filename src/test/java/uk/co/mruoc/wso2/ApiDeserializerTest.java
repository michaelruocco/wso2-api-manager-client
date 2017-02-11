package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiDeserializerTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";

    private final JsonLoader jsonLoader = new JsonLoader();
    private final ApiDeserializer deserializer = new ApiDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = loadJson("rest-product-api.json");

        Api api = deserializer.deserialize(element, null, null);

        assertThat(api).isEqualToComparingFieldByField(new RestProductApi());
    }

    private JsonElement loadJson(String filename) {
        String path = buildPath(filename);
        return jsonLoader.load(path);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
