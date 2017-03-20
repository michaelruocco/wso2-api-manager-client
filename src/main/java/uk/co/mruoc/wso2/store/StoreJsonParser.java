package uk.co.mruoc.wso2.store;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.mruoc.wso2.AbstractJsonParser;
import uk.co.mruoc.wso2.ApiManagerException;

public class StoreJsonParser extends AbstractJsonParser {

    private final JsonObject json;

    public StoreJsonParser(String jsonString) {
        this(new JsonParser().parse(jsonString));
    }

    public StoreJsonParser(JsonElement element) {
        this(element.getAsJsonObject());
    }

    public StoreJsonParser(JsonObject json) {
        this.json = json;
    }

    public String getApplication() {
        return toString(json, "application");
    }

    public String getName() {
        return toString(json, "name");
    }

    public int getId() {
        return toInt(json, "id");
    }

    public int getApplicationId() {
        return toInt(json, "applicationId");
    }

    @Override
    protected ApiManagerException createException(String message) {
        return new ApiStoreException(message);
    }

}
