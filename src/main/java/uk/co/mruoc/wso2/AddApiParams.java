package uk.co.mruoc.wso2;

import java.util.List;

public interface AddApiParams {

    String getName();

    String getContext();

    String getVersion();

    ApiVisibility getVisibility();

    List<String> getRoles();

    String getDescription();

    List<String> getTags();

    String getEndpointType();

    List<String> getTiers();

    boolean httpChecked();

    boolean httpsChecked();

    String getEndpointConfig();

    String getSwagger();

    EndpointSecurityTheme getEndpointSecurityTheme();

    String getEndpointUsername();

    String getEndpointPassword();

    String getInSequence();

    String getOutSequence();

    boolean isDefaultVersion();

    boolean isResponseCacheEnabled();

    ApiSubscriptions getSubscriptions();

    List<String> getTenants();

}
