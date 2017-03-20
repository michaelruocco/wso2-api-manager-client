package uk.co.mruoc.wso2.store.getsubscription;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import uk.co.mruoc.wso2.store.StoreJsonParser;

import java.lang.reflect.Type;

public class ApiSubscriptionDeserializer {

    public ApiSubscription deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) {
        StoreJsonParser parser = new StoreJsonParser(element);
        DefaultApiSubscription subscription = new DefaultApiSubscription();
        subscription.setApplicationName(parser.getApplication());
        subscription.setApplicationId(parser.getApplicationId());
        return subscription;
    }

}
