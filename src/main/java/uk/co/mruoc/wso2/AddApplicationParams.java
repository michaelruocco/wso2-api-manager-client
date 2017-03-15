package uk.co.mruoc.wso2;

public interface AddApplicationParams {

    String getApplicationName();

    ApiTierAvailability getTier();

    String getApplicationDescription();

    String getCallbackUrl();

}
