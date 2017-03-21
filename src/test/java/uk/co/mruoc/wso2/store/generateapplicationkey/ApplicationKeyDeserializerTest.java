package uk.co.mruoc.wso2.store.generateapplicationkey;

import com.google.gson.JsonElement;
import org.junit.Test;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationKeyDeserializerTest {

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final ApplicationKeyDeserializer deserializer = new ApplicationKeyDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = responseLoader.loadJson("test-application-key.json");

        ApplicationKey application = deserializer.deserialize(element, null, null);

        assertThat(application).isEqualToComparingFieldByField(new FakeApplicationKey());
    }

}
