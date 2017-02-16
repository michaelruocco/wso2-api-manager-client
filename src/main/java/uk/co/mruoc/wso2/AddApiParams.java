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

    List<ApiTierAvailability> getTiers();

    boolean httpChecked();

    boolean httpsChecked();

    String getEndpointConfig();

    String getSwagger();

    ApiEndpointType getApiEndpointType();

    String getEndpointUsername();

    String getEndpointPassword();

    String getInSequence();

    String getOutSequence();

    boolean isDefaultVersion();

    boolean isResponseCacheEnabled();

    int getResponseCacheTimeout();

    ApiSubscriptions getSubscriptions();

    List<String> getTenants();

}
