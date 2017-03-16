package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.StringArgumentBuilder;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.publisher.ApiSubscriptions.SPECIFIC_TENANTS;

public class SubscriptionsArgumentBuilder {

    private StringArgumentBuilder argumentBuilder = new StringArgumentBuilder("subscriptions");
    private StringArgumentBuilder tenantsArgumentBuilder = new StringArgumentBuilder("tenants");

    public String build(SubscriptionsParams params) {
        ApiSubscriptions subscriptions = params.getSubscriptions();
        String result = argumentBuilder.build(formatSubscriptionName(subscriptions));

        if (isSpecificTenants(subscriptions))
            result += tenantsArgumentBuilder.build(params.getTenants());

        return result;
    }

    private String formatSubscriptionName(ApiSubscriptions subscriptions) {
        if (subscriptions == null)
            return EMPTY;
        return subscriptions.name().toLowerCase();
    }

    private boolean isSpecificTenants(ApiSubscriptions subscriptions) {
        return SPECIFIC_TENANTS.equals(subscriptions);
    }

}
