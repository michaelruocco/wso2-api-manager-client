package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;

public class ResponseLoader {

    private static final String RESPONSE_PATH = "/uk/co/mruoc/wso2/";

    private final FileLoader fileLoader = new FileLoader();
    private final JsonLoader jsonLoader = new JsonLoader();

    public String getPath() {
        return RESPONSE_PATH;
    }

    public String load(String filename) {
        String path = getFullPath(filename);
        return fileLoader.loadContent(path);
    }

    public JsonElement loadJson(String filename) {
        String path = getFullPath(filename);
        return jsonLoader.load(path);
    }

    private String getFullPath(String filename) {
        String path = getPath();
        return path + filename;
    }



}
