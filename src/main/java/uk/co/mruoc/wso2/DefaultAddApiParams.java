package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultAddApiParams extends DefaultUpdateApiParams implements AddApiParams {

    private final String name;
    private final String context;
    private final String version;
    private final String inSequence;
    private final String outSequence;
    private final boolean defaultVersion;
    private final boolean responseCacheEnabled;
    private final int responseCacheTimeout;
    private final ApiSubscriptions subscriptions;
    private final List<String> tenants;

    private DefaultAddApiParams(DefaultAddApiParamsBuilder builder) {
        super(builder);
        this.name = builder.name;
        this.context = builder.context;
        this.version = builder.version;
        this.inSequence = builder.inSequence;
        this.outSequence = builder.outSequence;
        this.defaultVersion = builder.defaultVersion;
        this.responseCacheEnabled = builder.responseCacheEnabled;
        this.responseCacheTimeout = builder.responseCacheTimeout;
        this.subscriptions = builder.subscriptions;
        this.tenants = builder.tenants;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getInSequence() {
        return inSequence;
    }

    @Override
    public String getOutSequence() {
        return outSequence;
    }

    @Override
    public boolean isDefaultVersion() {
        return defaultVersion;
    }

    @Override
    public boolean isResponseCacheEnabled() {
        return responseCacheEnabled;
    }

    @Override
    public int getResponseCacheTimeout() {
        return responseCacheTimeout;
    }

    @Override
    public ApiSubscriptions getSubscriptions() {
        return subscriptions;
    }

    @Override
    public List<String> getTenants() {
        return tenants;
    }

    public static class DefaultAddApiParamsBuilder extends DefaultUpdateApiParamsBuilder<DefaultAddApiParamsBuilder> {

        private String name;
        private String context;
        private String version;
        private String inSequence;
        private String outSequence;
        private boolean defaultVersion;
        private boolean responseCacheEnabled;
        private int responseCacheTimeout;
        private ApiSubscriptions subscriptions = ApiSubscriptions.CURRENT_TENANT;
        private List<String> tenants = new ArrayList<>();

        public DefaultAddApiParamsBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DefaultAddApiParamsBuilder setContext(String context) {
            this.context = context;
            return this;
        }

        public DefaultAddApiParamsBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public DefaultAddApiParamsBuilder setInSequence(String inSequence) {
            this.inSequence = inSequence;
            return this;
        }

        public DefaultAddApiParamsBuilder setOutSequence(String outSequence) {
            this.outSequence = outSequence;
            return this;
        }

        public DefaultAddApiParamsBuilder setIsDefaultVersion(boolean defaultVersion) {
            this.defaultVersion = defaultVersion;
            return this;
        }

        public DefaultAddApiParamsBuilder setResponseCacheEnabled(boolean responseCacheEnabled) {
            this.responseCacheEnabled = responseCacheEnabled;
            return this;
        }

        public DefaultAddApiParamsBuilder setResponseCacheTimeout(int responseCacheTimeout) {
            this.responseCacheTimeout = responseCacheTimeout;
            return this;
        }

        public DefaultAddApiParamsBuilder setSubscriptions(ApiSubscriptions subscriptions) {
            this.subscriptions = subscriptions;
            return this;
        }

        public DefaultAddApiParamsBuilder setTenants(String... tenants) {
            this.tenants = Arrays.asList(tenants);
            return this;
        }

        public AddApiParams build() {
            return new DefaultAddApiParams(this);
        }

    }

}
