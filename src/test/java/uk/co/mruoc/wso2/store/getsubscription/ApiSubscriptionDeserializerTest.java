package uk.co.mruoc.wso2.store.getsubscription;

import com.google.gson.JsonElement;
import org.junit.Test;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSubscriptionDeserializerTest {

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final ApiSubscriptionDeserializer deserializer = new ApiSubscriptionDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = responseLoader.loadJson("test-subscription.json");

        ApiSubscription subscription = deserializer.deserialize(element, null, null);

        assertThat(subscription).isEqualToComparingFieldByField(new TestApiSubscription());
    }

}
