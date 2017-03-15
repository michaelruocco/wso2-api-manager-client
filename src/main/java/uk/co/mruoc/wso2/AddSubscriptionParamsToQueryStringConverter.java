package uk.co.mruoc.wso2;

public class AddSubscriptionParamsToQueryStringConverter {

    private final StringArgumentBuilder nameArgumentBuilder = new StringArgumentBuilder("application");
    private final StringArgumentBuilder tierArgumentBuilder = new StringArgumentBuilder("tier");

    public String convert(AddSubscriptionParams params) {
        return "?action=addAPISubscription" +
                nameArgumentBuilder.build(params.getApplicationName()) +
                tierArgumentBuilder.build(ApiAvailabilityTierNameFormatter.formatName(params.getTier()));
    }

}
