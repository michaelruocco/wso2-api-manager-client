package uk.co.mruoc.wso2.publisher.updateapi;

import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.ApiVisibilityParams;
import uk.co.mruoc.wso2.publisher.EndpointSecurityParams;
import uk.co.mruoc.wso2.publisher.TransportParams;

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
