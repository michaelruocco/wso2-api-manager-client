package uk.co.mruoc.wso2.publisher;

public enum ApiSubscriptions {

    CURRENT_TENANT,
    ALL_TENANTS,
    SPECIFIC_TENANTS;

    public static ApiSubscriptions parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
