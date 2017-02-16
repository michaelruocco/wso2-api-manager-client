package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultAddApiParams implements AddApiParams {

    private final String name;
    private final String context;
    private final String version;
    private final ApiVisibility visibility;
    private final List<String> roles;
    private final String description;
    private final List<String> tags;
    private final String endpointType;
    private final List<ApiTierAvailability> tiers;
    private final boolean httpChecked;
    private final boolean httpsChecked;
    private final String endpointConfig;
    private final String swagger;
    private final ApiEndpointType apiEndpointType;
    private final Credentials endpointCredentials;
    private final String inSequence;
    private final String outSequence;
    private final boolean defaultVersion;
    private final boolean responseCacheEnabled;
    private final int responseCacheTimeout;
    private final ApiSubscriptions subscriptions;
    private final List<String> tenants;

    private DefaultAddApiParams(DefaultAddApiParamsBuilder builder) {
        this.name = builder.name;
        this.context = builder.context;
        this.version = builder.version;
        this.visibility = builder.visibility;
        this.roles = builder.roles;
        this.description = builder.description;
        this.tags = builder.tags;
        this.endpointType = builder.endpointType;
        this.tiers = builder.tiers;
        this.httpChecked = builder.httpChecked;
        this.httpsChecked = builder.httpsChecked;
        this.endpointConfig = builder.endpointConfig;
        this.swagger = builder.swagger;
        this.apiEndpointType = builder.apiEndpointType;
        this.endpointCredentials = builder.endpointCredentials;
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
    public ApiVisibility getVisibility() {
        return visibility;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public String getEndpointType() {
        return endpointType;
    }

    @Override
    public List<ApiTierAvailability> getTiers() {
        return tiers;
    }

    @Override
    public boolean httpChecked() {
        return httpChecked;
    }

    @Override
    public boolean httpsChecked() {
        return httpsChecked;
    }

    @Override
    public String getEndpointConfig() {
        return endpointConfig;
    }

    @Override
    public String getSwagger() {
        return swagger;
    }

    public ApiEndpointType getApiEndpointType() {
        return apiEndpointType;
    }

    @Override
    public String getEndpointUsername() {
        if (hasEndpointCredentials())
            return endpointCredentials.getUsername();
        return "";
    }

    @Override
    public String getEndpointPassword() {
        if (hasEndpointCredentials())
            return endpointCredentials.getPassword();
        return "";
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

    private boolean hasEndpointCredentials() {
        return endpointCredentials != null;
    }

    public static class DefaultAddApiParamsBuilder {

        private String name;
        private String context;
        private String version;
        private ApiVisibility visibility = ApiVisibility.PUBLIC;
        private List<String> roles = new ArrayList<>();
        private String description;
        private List<String> tags = new ArrayList<>();
        private String endpointType;
        private List<ApiTierAvailability> tiers = Collections.singletonList(ApiTierAvailability.UNLIMITED);
        private boolean httpChecked = true;
        private boolean httpsChecked = true;
        private String endpointConfig;
        private String swagger;
        private ApiEndpointType apiEndpointType = ApiEndpointType.UNSECURED;
        private Credentials endpointCredentials;
        private String inSequence;
        private String outSequence;
        private boolean defaultVersion;
        private boolean responseCacheEnabled;
        private int responseCacheTimeout;
        private ApiSubscriptions subscriptions = ApiSubscriptions.CURRENT_TENANT;
        private List<String> tenants;

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

        public DefaultAddApiParamsBuilder setVisibility(ApiVisibility visibility) {
            this.visibility = visibility;
            return this;
        }

        public DefaultAddApiParamsBuilder setRoles(String... roles) {
            this.roles = Arrays.asList(roles);
            return this;
        }

        public DefaultAddApiParamsBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public DefaultAddApiParamsBuilder setTags(String... tags) {
            this.tags = Arrays.asList(tags);
            return this;
        }

        public DefaultAddApiParamsBuilder setEndpointType(String endpointType) {
            this.endpointType = endpointType;
            return this;
        }

        public DefaultAddApiParamsBuilder setTiers(ApiTierAvailability... tiers) {
            this.tiers = Arrays.asList(tiers);
            return this;
        }

        public DefaultAddApiParamsBuilder setHttpChecked(boolean httpChecked) {
            this.httpChecked = httpChecked;
            return this;
        }

        public DefaultAddApiParamsBuilder setHttpsChecked(boolean httpsChecked) {
            this.httpsChecked = httpsChecked;
            return this;
        }

        public DefaultAddApiParamsBuilder setEndpointConfig(String endpointConfig) {
            this.endpointConfig = endpointConfig;
            return this;
        }

        public DefaultAddApiParamsBuilder setSwagger(String swagger) {
            this.swagger = swagger;
            return this;
        }

        public DefaultAddApiParamsBuilder setApiEndpointType(ApiEndpointType apiEndpointType) {
            this.apiEndpointType = apiEndpointType;
            return this;
        }

        public DefaultAddApiParamsBuilder setEndpointCredentials(Credentials endpointCredentials) {
            this.endpointCredentials = endpointCredentials;
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

        public DefaultAddApiParamsBuilder setDefaultVersion(boolean defaultVersion) {
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

        public DefaultAddApiParamsBuilder setTenants(List<String> tenants) {
            this.tenants = tenants;
            return this;
        }

        public AddApiParams build() {
            return new DefaultAddApiParams(this);
        }

    }

}
