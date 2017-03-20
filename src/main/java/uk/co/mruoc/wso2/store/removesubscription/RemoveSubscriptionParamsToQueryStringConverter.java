package uk.co.mruoc.wso2.store.removesubscription;

import uk.co.mruoc.wso2.StringArgumentBuilder;

public class RemoveSubscriptionParamsToQueryStringConverter {

    private final StringArgumentBuilder applicationNameArgumentBuilder = new StringArgumentBuilder("applicationName");
    private final StringArgumentBuilder apiNameArgumentBuilder = new StringArgumentBuilder("name");
    private final StringArgumentBuilder versionArgumentBuilder = new StringArgumentBuilder("version");
    private final StringArgumentBuilder providerArgumentBuilder = new StringArgumentBuilder("provider");

    public String convert(RemoveSubscriptionParams params) {
        return "?action=removeSubscription" +
                apiNameArgumentBuilder.build(params.getApiName()) +
                versionArgumentBuilder.build(params.getApiVersion()) +
                providerArgumentBuilder.build(params.getProvider()) +
                applicationNameArgumentBuilder.build(params.getApplicationName());
    }


}
