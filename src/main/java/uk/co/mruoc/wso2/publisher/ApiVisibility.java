package uk.co.mruoc.wso2.publisher;

public enum ApiVisibility {

    PUBLIC,
    PRIVATE,
    RESTRICTED;

    public static ApiVisibility parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
