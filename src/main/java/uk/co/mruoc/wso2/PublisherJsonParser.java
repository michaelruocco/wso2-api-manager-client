package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;

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

    public String getStatus() {
        return toString(json, "status");
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
        if (value.isJsonNull())
            return false;
        return value.getAsBoolean();
    }

}
