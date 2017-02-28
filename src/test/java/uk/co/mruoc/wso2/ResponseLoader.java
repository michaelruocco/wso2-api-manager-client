package uk.co.mruoc.wso2;

public class ResponseLoader {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";

    private final FileLoader fileLoader = new FileLoader();

    public String load(String filename) {
        String path = buildPath(filename);
        return fileLoader.loadContent(path);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
