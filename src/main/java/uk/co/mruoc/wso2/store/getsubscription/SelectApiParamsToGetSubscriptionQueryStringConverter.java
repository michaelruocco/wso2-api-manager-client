package uk.co.mruoc.wso2.store.getsubscription;

import uk.co.mruoc.wso2.SelectApiParamsToQueryStringConverter;
import uk.co.mruoc.wso2.StringArgumentBuilder;

public class SelectApiParamsToGetSubscriptionQueryStringConverter extends SelectApiParamsToQueryStringConverter {

    public SelectApiParamsToGetSubscriptionQueryStringConverter() {
        super("getSubscriptionByAPI",
                new StringArgumentBuilder("apiName"),
                new StringArgumentBuilder("apiVersion"));
    }

}
