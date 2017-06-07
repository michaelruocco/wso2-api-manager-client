package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractJsonParser {

    protected abstract ApiManagerException createException(String message);

    protected String toString(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            return "";
        return value.getAsString();
    }

    protected int toInt(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            return 0;
        return value.getAsInt();
    }

    protected DateTime toDateTime(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            throw createException("could not parse date time from null json value for " + name);
        return new DateTime(value.getAsLong(), DateTimeZone.UTC);
    }

    protected boolean toBoolean(JsonObject json, String name) {
        JsonElement value = json.get(name);
        return !value.isJsonNull() && value.getAsBoolean();
    }

    protected List<String> toList(JsonObject json, String name) {
        JsonElement value = json.get(name);
        if (value.isJsonNull())
            return Collections.emptyList();
        return Arrays.asList(value.getAsString().split(","));
    }

    protected List<ApiTierAvailability> toTiers(List<String> values) {
        List<ApiTierAvailability> tiers = new ArrayList<>();
        values.forEach(v -> tiers.add(ApiTierAvailability.valueOf(v.toUpperCase())));
        return tiers;
    }

}
