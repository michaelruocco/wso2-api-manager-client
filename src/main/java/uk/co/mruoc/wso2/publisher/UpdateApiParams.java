package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.SelectApiParams;

import java.util.List;

public interface UpdateApiParams extends SelectApiParams, ApiVisibilityParams, TransportParams, EndpointSecurityParams {

    String getApiDescription();

    List<String> getTags();

    List<ApiTierAvailability> getTiers();

    String getEndpointConfig();

    String getSwagger();

    String getContext();

    String getThumbnailImageUrl();

}
