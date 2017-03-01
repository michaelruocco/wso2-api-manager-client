package uk.co.mruoc.wso2;

public enum ApiVisibility {

    PUBLIC,
    PRIVATE,
    RESTRICTED;

    public static ApiVisibility parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
