package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultAddApiParams extends DefaultUpdateApiParams implements AddApiParams, ApiVisibilityParams {

    private static final int DEFAULT_CACHE_TIMEOUT_IN_SECONDS = 300;

    private String context = EMPTY;
    private String inSequence = EMPTY;
    private String outSequence = EMPTY;
    private boolean defaultVersion;
    private boolean responseCacheEnabled;
    private int responseCacheTimeout = DEFAULT_CACHE_TIMEOUT_IN_SECONDS;
    private ApiSubscriptions subscriptions = ApiSubscriptions.CURRENT_TENANT;
    private List<String> tenants = new ArrayList<>();

    @Override
    public void setProvider(String provider) {
        throw new UnsupportedOperationException("setProvider not supported");
    }

    @Override
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String getInSequence() {
        return inSequence;
    }

    public void setInSequence(String inSequence) {
        this.inSequence = inSequence;
    }

    @Override
    public String getOutSequence() {
        return outSequence;
    }

    public void setOutSequence(String outSequence) {
        this.outSequence = outSequence;
    }

    @Override
    public boolean isDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(boolean defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    @Override
    public boolean isResponseCacheEnabled() {
        return responseCacheEnabled;
    }

    public void setResponseCacheEnabled(boolean responseCacheEnabled) {
        this.responseCacheEnabled = responseCacheEnabled;
    }

    @Override
    public int getResponseCacheTimeout() {
        return responseCacheTimeout;
    }

    public void setResponseCacheTimeout(int responseCacheTimeout) {
        this.responseCacheTimeout = responseCacheTimeout;
    }

    @Override
    public ApiSubscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ApiSubscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public List<String> getTenants() {
        return tenants;
    }

    public void setTenants(String... tenants) {
        this.tenants = Arrays.asList(tenants);
    }

}
