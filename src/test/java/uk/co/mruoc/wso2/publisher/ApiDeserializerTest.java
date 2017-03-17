package uk.co.mruoc.wso2.publisher;

import com.google.gson.JsonElement;
import org.junit.Test;
import uk.co.mruoc.wso2.ResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiDeserializerTest {

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final ApiDeserializer deserializer = new ApiDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = responseLoader.loadJson("rest-product-api.json");

        Api api = deserializer.deserialize(element, null, null);

        assertThat(api).isEqualToComparingFieldByField(new RestProductApi());
    }

}
