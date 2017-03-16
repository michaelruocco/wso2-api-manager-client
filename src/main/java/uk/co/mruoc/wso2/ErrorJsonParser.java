package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ErrorJsonParser {

    private final JsonObject json;

    public ErrorJsonParser(String jsonString) {
        this(new JsonParser().parse(jsonString));
    }

    public ErrorJsonParser(JsonElement element) {
        this(element.getAsJsonObject());
    }

    public ErrorJsonParser(JsonObject json) {
        this.json = json;
    }

    public boolean getError() {
        return toBoolean(json, "error");
    }

    private boolean toBoolean(JsonObject json, String name) {
        JsonElement value = json.get(name);
        return !value.isJsonNull() && value.getAsBoolean();
    }

}
