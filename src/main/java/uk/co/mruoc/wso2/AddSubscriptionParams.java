package uk.co.mruoc.wso2;

public interface AddSubscriptionParams extends SelectApiParams {

    String getApplicationName();

    ApiTierAvailability getTier();

}
