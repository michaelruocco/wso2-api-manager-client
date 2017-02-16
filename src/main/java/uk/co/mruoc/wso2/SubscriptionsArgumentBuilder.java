package uk.co.mruoc.wso2;

import static uk.co.mruoc.wso2.ApiSubscriptions.ALL_TENANTS;
import static uk.co.mruoc.wso2.ApiSubscriptions.SPECIFIC_TENANTS;

public class SubscriptionsArgumentBuilder {

    public String build(AddApiParams params) {
        ApiSubscriptions subscriptions = params.getSubscriptions();

        if (ALL_TENANTS.equals(subscriptions))
            return "&subscriptions=all_tenants";

        if (SPECIFIC_TENANTS.equals(subscriptions))
            return "&subscriptions=specific_tennats&tenants=" + UrlEncoder.encodeToCommaSeparatedList(params.getTenants());

        return "";
    }


}
