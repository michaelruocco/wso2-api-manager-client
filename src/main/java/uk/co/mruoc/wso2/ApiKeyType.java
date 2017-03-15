package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.List;

public enum ApiKeyType {

    PRODUCTION,
    SANDBOX;

    public static List<ApiKeyType> toKeyTypeList(List<String> names) {
        List<ApiKeyType> keyTypes = new ArrayList<>();
        names.forEach(n -> keyTypes.add(parse(n)));
        return keyTypes;
    }

    public static ApiKeyType parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
