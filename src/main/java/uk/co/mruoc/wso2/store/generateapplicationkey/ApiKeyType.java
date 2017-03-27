package uk.co.mruoc.wso2.store.generateapplicationkey;

import java.util.ArrayList;
import java.util.List;

public enum ApiKeyType {

    PRODUCTION,
    SANDBOX;

    public static List<ApiKeyType> toKeyTypeList(List<String> names) {
        List<ApiKeyType> keyTypes = new ArrayList<>();
        for (String name : names)
            keyTypes.add(parse(name));
        return keyTypes;
    }

    public static ApiKeyType parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
