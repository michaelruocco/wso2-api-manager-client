package uk.co.mruoc.wso2;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

public class Api {

    private final String name;
    private final String version;
    private final String description;
    private final DateTime lastUpdated;
    private final String context;
    private final int subscriberCount;
    private final String provider;
    private final ApiVisibility visibility;
    private final String status;
    private final String thumb;
    private final List<String> tags;
    private final ApiEndpointType endpointType;
    private final String endpointUsername;
    private final String endpointPassword;
    private final String endpointConfig;
    private final boolean httpChecked;
    private final boolean httpsChecked;
    private final List<ApiTierAvailability> tiers;
    private final List<String> roles;

    public Api(ApiBuilder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.description = builder.description;
        this.context = builder.context;
        this.lastUpdated = builder.lastUpdated;
        this.subscriberCount = builder.subscriberCount;
        this.provider = builder.provider;
        this.visibility = builder.visibility;
        this.status = builder.status;
        this.thumb = builder.thumb;
        this.tags = builder.tags;
        this.endpointType = builder.endpointType;
        this.endpointUsername = builder.endpointUsername;
        this.endpointPassword = builder.endpointPassword;
        this.endpointConfig = builder.endpointConfig;
        this.httpChecked = builder.httpChecked;
        this.httpsChecked = builder.httpsChecked;
        this.tiers = builder.tiers;
        this.roles = builder.roles;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getContext() {
        return context;
    }

    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public String getProvider() {
        return provider;
    }

    public ApiVisibility getVisibility() {
        return visibility;
    }

    public String getStatus() {
        return status;
    }

    public String getThumb() {
        return thumb;
    }

    public List<String> getTags() {
        return tags;
    }

    public ApiEndpointType getEndpointType() {
        return endpointType;
    }

    public String getEndpointUsername() {
        return endpointUsername;
    }

    public String getEndpointPassword() {
        return endpointPassword;
    }

    public String getEndpointConfig() {
        return endpointConfig;
    }

    public boolean isHttpChecked() {
        return httpChecked;
    }

    public boolean isHttpsChecked() {
        return httpsChecked;
    }

    public List<ApiTierAvailability> getTiers() {
        return tiers;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class ApiBuilder {

        private String name;
        private String version;
        private String description;
        private String context;
        private DateTime lastUpdated;
        private int subscriberCount;
        private String provider;
        private ApiVisibility visibility;
        private String status;
        private String thumb;
        private List<String> tags;
        private ApiEndpointType endpointType;
        private String endpointUsername;
        private String endpointPassword;
        private String endpointConfig;
        private boolean httpChecked;
        private boolean httpsChecked;
        private List<ApiTierAvailability> tiers;
        private List<String> roles;

        public ApiBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ApiBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public ApiBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ApiBuilder setContext(String context) {
            this.context = context;
            return this;
        }

        public ApiBuilder setLastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public ApiBuilder setSubscriberCount(int subscriberCount) {
            this.subscriberCount = subscriberCount;
            return this;
        }

        public ApiBuilder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public ApiBuilder setVisibility(ApiVisibility visibility) {
            this.visibility = visibility;
            return this;
        }

        public ApiBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ApiBuilder setThumb(String thumb) {
            this.thumb = thumb;
            return this;
        }

        public ApiBuilder setTags(String... tags) {
            return setTags(Arrays.asList(tags));
        }

        public ApiBuilder setTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public ApiBuilder setEndpointType(ApiEndpointType endpointType) {
            this.endpointType = endpointType;
            return this;
        }

        public ApiBuilder setEndpointUsername(String endpointUsername) {
            this.endpointUsername = endpointUsername;
            return this;
        }

        public ApiBuilder setEndpointPassword(String endpointPassword) {
            this.endpointPassword = endpointPassword;
            return this;
        }

        public ApiBuilder setEndpointConfig(String endpointConfig) {
            this.endpointConfig = endpointConfig;
            return this;
        }

        public ApiBuilder setHttpChecked(boolean httpChecked) {
            this.httpChecked = httpChecked;
            return this;
        }

        public ApiBuilder setHttpsChecked(boolean httpsChecked) {
            this.httpsChecked = httpsChecked;
            return this;
        }

        public ApiBuilder setTiers(ApiTierAvailability... tiers) {
            return setTiers(Arrays.asList(tiers));
        }

        public ApiBuilder setTiers(List<ApiTierAvailability> tiers) {
            this.tiers = tiers;
            return this;
        }

        public ApiBuilder setRoles(String... roles) {
            return setRoles(Arrays.asList(roles));
        }

        public ApiBuilder setRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public Api build() {
            return new Api(this);
        }

    }

}
