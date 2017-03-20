package uk.co.mruoc.wso2.store.addsubscription;

import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.SelectApiParams;

public interface AddSubscriptionParams extends SelectApiParams {

    String getApplicationName();

    ApiTierAvailability getTier();

}
