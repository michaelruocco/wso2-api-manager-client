package uk.co.mruoc.wso2;

public enum ApiStatus {

    CREATED,
    PROTOTYPED,
    PUBLISHED,
    DEPRECATED,
    RETIRED,
    BLOCKED;

    public static ApiStatus parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
