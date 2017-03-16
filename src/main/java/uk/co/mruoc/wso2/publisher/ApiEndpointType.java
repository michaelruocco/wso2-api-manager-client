package uk.co.mruoc.wso2.publisher;

public enum ApiEndpointType {

    SECURED,
    UNSECURED;

    public static ApiEndpointType parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
