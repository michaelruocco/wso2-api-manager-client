package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonLoader {

    private final FileLoader fileLoader = new FileLoader();

    public JsonElement load(String path) {
        String content = fileLoader.loadContent(path);
        JsonParser parser = new JsonParser();
        return parser.parse(content);
    }

}
