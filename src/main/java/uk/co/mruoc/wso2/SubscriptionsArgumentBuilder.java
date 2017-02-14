package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static uk.co.mruoc.wso2.ApiSubscriptions.ALL_TENANTS;
import static uk.co.mruoc.wso2.ApiSubscriptions.SPECIFIC_TENANTS;

public class SubscriptionsArgumentBuilder {

    public String build(AddApiParams params) {
        ApiSubscriptions subscriptions = params.getSubscriptions();

        if (ALL_TENANTS.equals(subscriptions))
            return "&subscriptions=all_tenants";

        if (SPECIFIC_TENANTS.equals(subscriptions))
            return "&subscriptions=specific_tennats&tenants=" + toCommaSeparatedString(params.getTenants());

        return "";
    }

    private String toCommaSeparatedString(List<String> values) {
        return StringUtils.join(values, ",");
    }

}
