package uk.co.mruoc.wso2;

import java.util.List;

public interface AddApiParams extends UpdateApiParams {

    String getName();

    String getContext();

    String getVersion();

    String getInSequence();

    String getOutSequence();

    boolean isDefaultVersion();

    boolean isResponseCacheEnabled();

    int getResponseCacheTimeout();

    ApiSubscriptions getSubscriptions();

    List<String> getTenants();

}
