package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ListToCommaSeparatedStringConverter {

    public static String toCommaSeparatedString(List<String> values) {
        return StringUtils.join(values, ",");
    }

}
