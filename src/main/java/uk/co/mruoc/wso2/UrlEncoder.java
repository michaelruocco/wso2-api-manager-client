package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        values.replaceAll(String::trim);
        values.replaceAll(UrlEncoder::encode);
        return CommaSeparatedStringConverter.toString(values);
    }

}
