package uk.co.mruoc.wso2;

import static uk.co.mruoc.wso2.ApiSubscriptions.SPECIFIC_TENANTS;

public class SubscriptionsArgumentBuilder {

    public String build(SubscriptionsParams params) {
        ApiSubscriptions subscriptions = params.getSubscriptions();
        String result = "subscriptions=" + UrlEncoder.encode(subscriptions.name().toLowerCase());

        if (SPECIFIC_TENANTS.equals(subscriptions))
            result += "&tenants=" + UrlEncoder.encodeToCommaSeparatedList(params.getTenants());

        return result;
    }


}
