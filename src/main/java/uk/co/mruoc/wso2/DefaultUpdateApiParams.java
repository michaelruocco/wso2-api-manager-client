package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultUpdateApiParams implements UpdateApiParams {

    private final ApiVisibility visibility;
    private final List<String> roles;
    private final String description;
    private final List<String> tags;
    private final ApiEndpointType endpointType;
    private final String endpointUsername;
    private final String endpointPassword;
    private final List<ApiTierAvailability> tiers;
    private final boolean httpChecked;
    private final boolean httpsChecked;
    private final String endpointConfig;
    private final String swagger;

    protected DefaultUpdateApiParams(DefaultUpdateApiParamsBuilder builder) {
        this.visibility = builder.visibility;
        this.roles = builder.roles;
        this.description = builder.description;
        this.tags = builder.tags;
        this.endpointType = builder.endpointType;
        this.endpointUsername = builder.endpointUsername;
        this.endpointPassword = builder.endpointPassword;
        this.tiers = builder.tiers;
        this.httpChecked = builder.httpChecked;
        this.httpsChecked = builder.httpsChecked;
        this.endpointConfig = builder.endpointConfig;
        this.swagger = builder.swagger;
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
    public ApiEndpointType getEndpointType() {
        return endpointType;
    }

    @Override
    public String getEndpointUsername() {
        return endpointUsername;
    }

    @Override
    public String getEndpointPassword() {
        return endpointPassword;
    }

    @Override
    public List<ApiTierAvailability> getTiers() {
        return tiers;
    }

    @Override
    public boolean isHttpChecked() {
        return httpChecked;
    }

    @Override
    public boolean isHttpsChecked() {
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

    public static class DefaultUpdateApiParamsBuilder<T extends DefaultUpdateApiParamsBuilder> {

        private ApiVisibility visibility = ApiVisibility.PUBLIC;
        private List<String> roles = new ArrayList<>();
        private String description;
        private List<String> tags = new ArrayList<>();
        private ApiEndpointType endpointType = ApiEndpointType.UNSECURED;
        private String endpointUsername;
        private String endpointPassword;
        private List<ApiTierAvailability> tiers = Collections.singletonList(ApiTierAvailability.UNLIMITED);
        private boolean httpChecked = true;
        private boolean httpsChecked = true;
        private String endpointConfig;
        private String swagger;

        public T setVisibility(ApiVisibility visibility) {
            this.visibility = visibility;
            return (T)this;
        }

        public T setRoles(String... roles) {
            this.roles = Arrays.asList(roles);
            return (T)this;
        }

        public T setDescription(String description) {
            this.description = description;
            return (T)this;
        }

        public T setTags(String... tags) {
            this.tags = Arrays.asList(tags);
            return (T)this;
        }

        public T setEndpointType(ApiEndpointType endpointType) {
            this.endpointType = endpointType;
            return (T)this;
        }

        public T setEndpointUsername(String endpointUsername) {
            this.endpointUsername = endpointUsername;
            return (T)this;
        }

        public T setEndpointPassword(String endpointPassword) {
            this.endpointPassword = endpointPassword;
            return (T)this;
        }

        public T setTiers(ApiTierAvailability... tiers) {
            this.tiers = Arrays.asList(tiers);
            return (T)this;
        }

        public T setHttpChecked(boolean httpChecked) {
            this.httpChecked = httpChecked;
            return (T)this;
        }

        public T setHttpsChecked(boolean httpsChecked) {
            this.httpsChecked = httpsChecked;
            return (T)this;
        }

        public T setEndpointConfig(String endpointConfig) {
            this.endpointConfig = endpointConfig;
            return (T)this;
        }

        public T setSwagger(String swagger) {
            this.swagger = swagger;
            return (T)this;
        }

        public UpdateApiParams build() {
            return new DefaultUpdateApiParams(this);
        }

    }

}
