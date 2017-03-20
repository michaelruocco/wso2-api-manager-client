package uk.co.mruoc.wso2.publisher.addapi;

import uk.co.mruoc.wso2.publisher.ResponseCacheParams;
import uk.co.mruoc.wso2.publisher.SequenceParams;
import uk.co.mruoc.wso2.publisher.SubscriptionsParams;
import uk.co.mruoc.wso2.publisher.updateapi.UpdateApiParams;

public interface AddApiParams extends UpdateApiParams, SequenceParams, SubscriptionsParams, ResponseCacheParams {

    String getApiName();

    String getContext();

    String getApiVersion();

    boolean isDefaultVersion();

    String getProvider();

}
