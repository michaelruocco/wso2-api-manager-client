package uk.co.mruoc.wso2.store;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;
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

    public DateTime getValidityTime() {
        return toDateTime(json, "validityTime");
    }

    public String getConsumerKey() {
        return toString(json, "consumerKey");
    }

    public String getConsumerSecret() {
        return toString(json, "consumerSecret");
    }

    public String getAccessToken() {
        return toString(json, "accessToken");
    }

    @Override
    protected ApiManagerException createException(String message) {
        return new ApiStoreException(message);
    }

}
