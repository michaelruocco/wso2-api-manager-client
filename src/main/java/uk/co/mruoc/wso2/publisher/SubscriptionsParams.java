package uk.co.mruoc.wso2.publisher;

import java.util.List;

public interface SubscriptionsParams {

    ApiSubscriptions getSubscriptions();

    List<String> getTenants();

}
