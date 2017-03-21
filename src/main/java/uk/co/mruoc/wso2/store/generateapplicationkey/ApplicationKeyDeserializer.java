package uk.co.mruoc.wso2.store.generateapplicationkey;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import uk.co.mruoc.wso2.store.StoreJsonParser;

import java.lang.reflect.Type;

public class ApplicationKeyDeserializer implements JsonDeserializer<ApplicationKey> {

    @Override
    public ApplicationKey deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        StoreJsonParser parser = new StoreJsonParser(element);
        DefaultApplicationKey application = new DefaultApplicationKey();
        application.setValidityTime(parser.getValidityTime());
        application.setConsumerKey(parser.getConsumerKey());
        application.setConsumerSecret(parser.getConsumerSecret());
        application.setAccessToken(parser.getAccessToken());
        return application;
    }

}
