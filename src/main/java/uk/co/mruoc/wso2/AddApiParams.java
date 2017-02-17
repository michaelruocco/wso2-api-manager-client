package uk.co.mruoc.wso2;

public interface AddApiParams extends UpdateApiParams, SubscriptionsParams {

    String getName();

    String getContext();

    String getVersion();

    String getInSequence();

    String getOutSequence();

    boolean isDefaultVersion();

    boolean isResponseCacheEnabled();

    int getResponseCacheTimeout();

}
