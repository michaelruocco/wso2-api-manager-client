package uk.co.mruoc.wso2;

import java.util.List;

public interface UpdateApiParams extends ApiVisibilityParams {

    String getDescription();

    List<String> getTags();

    ApiEndpointType getEndpointType();

    String getEndpointUsername();

    String getEndpointPassword();

    List<ApiTierAvailability> getTiers();

    boolean isHttpChecked();

    boolean isHttpsChecked();

    String getEndpointConfig();

    String getSwagger();

}
