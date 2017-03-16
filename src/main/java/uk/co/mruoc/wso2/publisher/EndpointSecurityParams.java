package uk.co.mruoc.wso2.publisher;

public interface EndpointSecurityParams {

    ApiEndpointType getEndpointType();

    String getEndpointUsername();

    String getEndpointPassword();

}
