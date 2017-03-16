package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.DefaultSelectApiParams;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultAddSubscriptionParams extends DefaultSelectApiParams implements AddSubscriptionParams {

    private String applicationName = EMPTY;
    private ApiTierAvailability tier = ApiTierAvailability.UNLIMITED;

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setTier(ApiTierAvailability tier) {
        this.tier = tier;
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public ApiTierAvailability getTier() {
        return tier;
    }

}
