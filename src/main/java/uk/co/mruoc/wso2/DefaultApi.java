package uk.co.mruoc.wso2;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultApi implements Api {

    private String name = EMPTY;
    private String version = EMPTY;
    private String description = EMPTY;
    private String context = EMPTY;
    private DateTime lastUpdated = new DateTime(Long.MIN_VALUE);
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public int getSubscriberCount() {
        return subscriberCount;
    }

    @Override
    public String getProvider() {
        return provider;
    }

    @Override
    public ApiVisibility getVisibility() {
        return visibility;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getThumb() {
        return thumb;
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
    public String getEndpointConfig() {
        return endpointConfig;
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
    public List<ApiTierAvailability> getTiers() {
        return tiers;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setLastUpdated(DateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setVisibility(ApiVisibility visibility) {
        this.visibility = visibility;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setTags(String... tags) {
        setTags(Arrays.asList(tags));
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setEndpointType(ApiEndpointType endpointType) {
        this.endpointType = endpointType;
    }

    public void setEndpointUsername(String endpointUsername) {
        this.endpointUsername = endpointUsername;
    }

    public void setEndpointPassword(String endpointPassword) {
        this.endpointPassword = endpointPassword;
    }

    public void setEndpointConfig(String endpointConfig) {
        this.endpointConfig = endpointConfig;
    }

    public void setHttpChecked(boolean httpChecked) {
        this.httpChecked = httpChecked;
    }

    public void setHttpsChecked(boolean httpsChecked) {
        this.httpsChecked = httpsChecked;
    }

    public void setTiers(ApiTierAvailability... tiers) {
        setTiers(Arrays.asList(tiers));
    }

    public void setTiers(List<ApiTierAvailability> tiers) {
        this.tiers = tiers;
    }

    public void setRoles(String... roles) {
        setRoles(Arrays.asList(roles));
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
