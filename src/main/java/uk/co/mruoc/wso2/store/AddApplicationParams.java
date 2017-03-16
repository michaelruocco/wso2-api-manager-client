package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.ApiTierAvailability;

public interface AddApplicationParams {

    String getApplicationName();

    ApiTierAvailability getTier();

    String getApplicationDescription();

    String getCallbackUrl();

}
