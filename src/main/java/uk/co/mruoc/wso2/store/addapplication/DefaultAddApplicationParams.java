package uk.co.mruoc.wso2.store.addapplication;

import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationParams;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;

public class DefaultAddApplicationParams implements AddApplicationParams {

    private String name = EMPTY;
    private ApiTierAvailability tier = UNLIMITED;
    private String description = EMPTY;
    private String callbackUrl = EMPTY;

    @Override
    public String getApplicationName() {
        return name;
    }

    @Override
    public ApiTierAvailability getTier() {
        return tier;
    }

    @Override
    public String getApplicationDescription() {
        return description;
    }

    @Override
    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setApplicationName(String name) {
        this.name = name;
    }

    public void setTier(ApiTierAvailability tier) {
        this.tier = tier;
    }

    public void setApplicationDescription(String description) {
        this.description = description;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

}
