package uk.co.mruoc.wso2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class UrlEncoder {

    private static final String DEFAULT_ENCODING = "utf-8";

    public static String encode(int value) {
        return encode(Integer.toString(value));
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new ApiPublisherException("error url encoding value " + value, e);
        }
    }

    public static String encodeToCommaSeparatedList(List<String> values) {
        values.replaceAll(UrlEncoder::encode);
        return ListToCommaSeparatedStringConverter.toCommaSeparatedString(values);
    }

}
