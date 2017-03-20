package uk.co.mruoc.wso2.store.listallapplications;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import uk.co.mruoc.wso2.store.StoreJsonParser;

import java.lang.reflect.Type;

public class ApiApplicationDeserializer implements JsonDeserializer<ApiApplication> {

    @Override
    public ApiApplication deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) {
        StoreJsonParser parser = new StoreJsonParser(element);
        DefaultApiApplication application = new DefaultApiApplication();
        application.setApplicationName(parser.getName());
        application.setApplicationId(parser.getId());
        return application;
    }

}
