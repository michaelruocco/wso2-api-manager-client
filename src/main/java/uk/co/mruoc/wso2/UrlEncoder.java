package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlEncoder {

    private static final String DEFAULT_ENCODING = "utf-8";

    public static String encode(String value) {
        try {
            if (StringUtils.isEmpty(value))
                return "";
            return URLEncoder.encode(value, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new ApiManagerException("error url encoding value " + value, e);
        }
    }

    public static String encodeToCommaSeparatedList(List<String> values) {
        List<String> trimmedValues = trimAll(values);
        List<String> encodedValues = encodeAll(trimmedValues);
        return CommaSeparatedStringConverter.toString(encodedValues);
    }

    private static List<String> trimAll(List<String> values) {
        List<String> trimmedValues = new ArrayList<>();
        for (String value : values)
            trimmedValues.add(value.trim());
        return trimmedValues;
    }

    private static List<String> encodeAll(List<String> values) {
        List<String> encodedValues = new ArrayList<>();
        for (String value : values)
            encodedValues.add(UrlEncoder.encode(value));
        return encodedValues;
    }

}
