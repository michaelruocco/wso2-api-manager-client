package uk.co.mruoc.wso2.store;

import com.google.gson.JsonElement;
import org.junit.Test;
import uk.co.mruoc.wso2.ResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiApplicationDeserializerTest {

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final ApiApplicationDeserializer deserializer = new ApiApplicationDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = responseLoader.loadJson("test-application.json");

        ApiApplication application = deserializer.deserialize(element, null, null);

        assertThat(application).isEqualToComparingFieldByField(new TestApplication());
    }

}
