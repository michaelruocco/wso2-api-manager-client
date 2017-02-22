package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PublisherJsonParser {

    private final JsonObject json;

    public PublisherJsonParser(String jsonString) {
        this(new JsonParser().parse(jsonString));
    }

    public PublisherJsonParser(JsonElement element) {
        this(element.getAsJsonObject());
    }

    public PublisherJsonParser(JsonObject json) {
        this.json = json;
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
        return toBoolean(json, "error");
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
        return value.equals("checked");
    }

    public boolean getHttpsChecked() {
        String value = toString(json, "transport_https");
        return value.equals("checked");
    }

    public List<ApiTierAvailability> getTiers() {
        List<String> values = toList(json, "availableTiers");
        return toTiers(values);
    }

    public List<String> getRoles() {
        return toList(json, "roles");
    }

    private static String toString(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            return "";
        return value.getAsString();
    }

    private static int toInt(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            return 0;
        return value.getAsInt();
    }

    private static DateTime toDateTime(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            throw new ApiPublisherException("could not parse date time from null json value for " + name);
        return new DateTime(value.getAsLong());
    }

    private static boolean toBoolean(JsonObject json, String name) {
        JsonElement value = json.get(name);
        return !value.isJsonNull() && value.getAsBoolean();
    }

    private static ApiVisibility toVisibility(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            throw new ApiPublisherException(name + " cannot be null");
        return ApiVisibility.valueOf(value.getAsString().toUpperCase());
    }

    private static List<String> toList(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            return Collections.emptyList();
        return Arrays.asList(value.getAsString().split(","));
    }

    private static List<ApiTierAvailability> toTiers(List<String> values) {
        List<ApiTierAvailability> tiers = new ArrayList<>();
        values.forEach(v -> tiers.add(ApiTierAvailability.valueOf(v.toUpperCase())));
        return tiers;
    }

    private static ApiStatus toStatus(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            throw new ApiPublisherException(name + " cannot be null");
        return ApiStatus.valueOf(value.getAsString().toUpperCase());
    }

}
