package uk.co.mruoc.wso2;

public interface AddApiParams extends UpdateApiParams, SequenceParams, SubscriptionsParams, ResponseCacheParams {

    String getName();

    String getContext();

    String getVersion();

    boolean isDefaultVersion();

}
