package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommaSeparatedStringConverter {

    private static final String COMMA = ",";

    public static String toString(List<String> values) {
        return StringUtils.join(values, COMMA);
    }

    public static List<String> toList(String value) {
        List<String> list = Arrays.asList(value.split(COMMA));
        return trimAllElements(list);
    }

    private static List<String> trimAllElements(List<String> inputs) {
        List<String> results = new ArrayList<>();
        for (String input : inputs)
            results.add(input.trim());
        return results;
    }

}
