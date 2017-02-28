package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.ApiEndpointType.*;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;
import static uk.co.mruoc.wso2.ApiVisibility.*;

public class DefaultUpdateApiParams extends DefaultSelectApiParams implements UpdateApiParams {

    private ApiVisibility visibility = PUBLIC;
    private List<String> roles = new ArrayList<>();
    private String description = EMPTY;
    private List<String> tags = new ArrayList<>();
    private ApiEndpointType endpointType = UNSECURED;
    private String endpointUsername = EMPTY;
    private String endpointPassword = EMPTY;
    private List<ApiTierAvailability> tiers = Collections.singletonList(UNLIMITED);
    private boolean httpChecked = true;
    private boolean httpsChecked = true;
    private String endpointConfig = EMPTY;
    private String swagger = EMPTY;
    private String context = EMPTY;
    private String thumbnailImagePath = EMPTY;

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

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public String getThumbnailImagePath() {
        return thumbnailImagePath;
    }

    public void setVisibility(ApiVisibility visibility) {
        this.visibility = visibility;
    }

    public void setRoles(String... roles) {
        setRoles(Arrays.asList(roles));
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setTiers(ApiTierAvailability... tiers) {
        setTiers(Arrays.asList(tiers));
    }

    public void setTiers(List<ApiTierAvailability> tiers) {
        this.tiers = tiers;
    }

    public void setHttpChecked(boolean httpChecked) {
        this.httpChecked = httpChecked;
    }

    public void setHttpsChecked(boolean httpsChecked) {
        this.httpsChecked = httpsChecked;
    }

    public void setEndpointConfig(String endpointConfig) {
        this.endpointConfig = endpointConfig;
    }

    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setThumbnailImagePath(String thumbnailImagePath) {
        this.thumbnailImagePath = thumbnailImagePath;
    }

}
