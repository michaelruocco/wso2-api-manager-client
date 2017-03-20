package uk.co.mruoc.wso2.store.addsubscription;

import uk.co.mruoc.wso2.ApiAvailabilityTierNameFormatter;
import uk.co.mruoc.wso2.StringArgumentBuilder;

public class AddSubscriptionParamsToQueryStringConverter {

    private final StringArgumentBuilder applicationNameArgumentBuilder = new StringArgumentBuilder("applicationName");
    private final StringArgumentBuilder tierArgumentBuilder = new StringArgumentBuilder("tier");
    private final StringArgumentBuilder apiNameArgumentBuilder = new StringArgumentBuilder("name");
    private final StringArgumentBuilder versionArgumentBuilder = new StringArgumentBuilder("version");
    private final StringArgumentBuilder providerArgumentBuilder = new StringArgumentBuilder("provider");

    public String convert(AddSubscriptionParams params) {
        return "?action=addAPISubscription" +
                apiNameArgumentBuilder.build(params.getApiName()) +
                versionArgumentBuilder.build(params.getApiVersion()) +
                providerArgumentBuilder.build(params.getProvider()) +
                applicationNameArgumentBuilder.build(params.getApplicationName()) +
                tierArgumentBuilder.build(ApiAvailabilityTierNameFormatter.formatName(params.getTier()));
    }

}
