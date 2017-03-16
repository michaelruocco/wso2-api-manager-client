package uk.co.mruoc.wso2.publisher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;
import uk.co.mruoc.wso2.AbstractJsonParser;
import uk.co.mruoc.wso2.ApiManagerException;
import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.ErrorJsonParser;

import java.util.List;

public class PublisherJsonParser extends AbstractJsonParser {

    private final JsonObject json;
    private final ErrorJsonParser errorParser;

    public PublisherJsonParser(String jsonString) {
        this(new JsonParser().parse(jsonString));
    }

    public PublisherJsonParser(JsonElement element) {
        this(element.getAsJsonObject());
    }

    public PublisherJsonParser(JsonObject json) {
        this.json = json;
        this.errorParser = new ErrorJsonParser(json);
    }

    public String getName() {
        return toString(json, "name");
    }

    public String getVersion() {
        return toString(json, "version");
    }

    public String getDescription() {
        return toString(json, "description");
    }

    public String getProvider() {
        return toString(json, "provider");
    }

    public ApiStatus getStatus() {
        return toStatus(json, "status");
    }

    public String getThumbnailImagePath() {
        return toString(json, "thumb");
    }

    public String getContext() {
        return toString(json, "context");
    }

    public DateTime getLastUpdated() {
        return toDateTime(json, "lastUpdated");
    }

    public int getSubscriberCount() {
        return toInt(json, "subs");
    }

    public boolean getError() {
        return errorParser.getError();
    }

    public boolean getExists() {
        return toBoolean(json, "exist");
    }

    public ApiVisibility getVisibility() {
        return toVisibility(json, "visibility");
    }

    public List<String> getTags() {
        return toList(json, "tags");
    }

    public ApiEndpointType getEndpointType() {
        boolean secured = toBoolean(json, "endpointTypeSecured");
        if (secured)
            return ApiEndpointType.SECURED;
        return ApiEndpointType.UNSECURED;
    }

    public String getEndpointUsername() {
        return toString(json, "epUsername");
    }

    public String getEndpointPassword() {
        return toString(json, "epPassword");
    }

    public String getEndpointConfig() {
        return toString(json, "endpointConfig");
    }

    public boolean getHttpChecked() {
        String value = toString(json, "transport_http");
        return isChecked(value);
    }

    public boolean getHttpsChecked() {
        String value = toString(json, "transport_https");
        return isChecked(value);
    }

    private boolean isChecked(String value) {
        return "checked".equals(value);
    }

    public List<ApiTierAvailability> getTiers() {
        List<String> values = toList(json, "availableTiers");
        return toTiers(values);
    }

    public List<String> getRoles() {
        return toList(json, "roles");
    }

    @Override
    protected ApiManagerException createException(String message) {
        return new ApiPublisherException(message);
    }

    private ApiStatus toStatus(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            throw createException(name + " cannot be null");
        return ApiStatus.valueOf(value.getAsString().toUpperCase());
    }

    private ApiVisibility toVisibility(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            throw createException(name + " cannot be null");
        return ApiVisibility.valueOf(value.getAsString().toUpperCase());
    }

}
