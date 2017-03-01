package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.List;

public enum ApiSubscriptions {

    CURRENT_TENANT,
    ALL_TENANTS,
    SPECIFIC_TENANTS;

    public static List<ApiSubscriptions> toSubscriptionsList(List<String> names) {
        List<ApiSubscriptions> subscriptions = new ArrayList<>();
        names.forEach(n -> subscriptions.add(parse(n)));
        return subscriptions;
    }

    public static ApiSubscriptions parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
