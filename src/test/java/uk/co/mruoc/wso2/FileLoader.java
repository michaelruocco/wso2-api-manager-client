package uk.co.mruoc.wso2;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class FileLoader {

    private static final String DEFAULT_ENCODING = "UTF-8";

    public String loadContent(String path) {
        try {
            InputStream stream = toStream(path);
            return IOUtils.toString(stream, DEFAULT_ENCODING);
        } catch (IOException e) {
            throw new FileLoadException(e);
        }
    }

    private InputStream toStream(String path) {
        return loadFromClasspath(path);
    }

    private InputStream loadFromClasspath(String path) {
        return getClass().getResourceAsStream(path);
    }

}
