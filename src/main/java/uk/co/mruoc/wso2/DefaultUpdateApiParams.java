package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.ApiEndpointType.*;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;
import static uk.co.mruoc.wso2.ApiVisibility.*;

public class DefaultUpdateApiParams extends DefaultGetApiParams implements UpdateApiParams {

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

    @Override
    public void setProvider(String provider) {
        throw new UnsupportedOperationException("setProvider not supported");
    }

    @Override
    public ApiVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(ApiVisibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = Arrays.asList(roles);
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags = Arrays.asList(tags);
    }

    @Override
    public ApiEndpointType getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(ApiEndpointType endpointType) {
        this.endpointType = endpointType;
    }

    @Override
    public String getEndpointUsername() {
        return endpointUsername;
    }

    public void setEndpointUsername(String endpointUsername) {
        this.endpointUsername = endpointUsername;
    }

    @Override
    public String getEndpointPassword() {
        return endpointPassword;
    }

    public void setEndpointPassword(String endpointPassword) {
        this.endpointPassword = endpointPassword;
    }

    @Override
    public List<ApiTierAvailability> getTiers() {
        return tiers;
    }

    public void setTiers(ApiTierAvailability... tiers) {
        this.tiers = Arrays.asList(tiers);
    }

    @Override
    public boolean isHttpChecked() {
        return httpChecked;
    }

    public void setHttpChecked(boolean httpChecked) {
        this.httpChecked = httpChecked;
    }

    @Override
    public boolean isHttpsChecked() {
        return httpsChecked;
    }

    public void setHttpsChecked(boolean httpsChecked) {
        this.httpsChecked = httpsChecked;
    }

    @Override
    public String getEndpointConfig() {
        return endpointConfig;
    }

    public void setEndpointConfig(String endpointConfig) {
        this.endpointConfig = endpointConfig;
    }

    @Override
    public String getSwagger() {
        return swagger;
    }

    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

}
