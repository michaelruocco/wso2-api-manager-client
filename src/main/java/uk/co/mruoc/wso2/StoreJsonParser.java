package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public String getName() {
        return toString(json, "name");
    }

    public ApiTierAvailability getTier() {
        String tier = toString(json, "tier");
        return ApiTierAvailability.valueOf(tier.toUpperCase());
    }

    public int getId() {
        return toInt(json, "id");
    }

    @Override
    protected ApiManagerException createException(String message) {
        return new ApiStoreException(message);
    }

}
