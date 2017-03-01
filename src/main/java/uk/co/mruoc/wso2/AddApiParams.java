package uk.co.mruoc.wso2;

public interface AddApiParams extends UpdateApiParams, SequenceParams, SubscriptionsParams, ResponseCacheParams {

    String getApiName();

    String getContext();

    String getApiVersion();

    boolean isDefaultVersion();

}
