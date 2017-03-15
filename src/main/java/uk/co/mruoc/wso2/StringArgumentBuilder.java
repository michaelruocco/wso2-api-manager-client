package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class StringArgumentBuilder {

    private final String name;

    public StringArgumentBuilder(String name) {
        this.name = name;
    }

    public String build(String value) {
        String encodedValue = UrlEncoder.encode(value);
        return buildArgument(encodedValue);
    }

    public String build(List<String> values) {
        String encodedValue = UrlEncoder.encodeToCommaSeparatedList(values);
        return buildArgument(encodedValue);
    }

    public String build(boolean value) {
        String encodedValue = Boolean.toString(value);
        return buildArgument(encodedValue);
    }

    private String buildArgument(String encodedValue) {
        return prefixAmpersand(toNameValuePair(encodedValue));
    }

    private String toNameValuePair(String value) {
        return name + "=" + value;
    }

    private String prefixAmpersand(String value) {
        return "&" + value;
    }

}
