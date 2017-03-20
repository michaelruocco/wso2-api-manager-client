package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.store.addsubscription.DefaultAddSubscriptionParams;

public class FakeAddSubscriptionParams extends DefaultAddSubscriptionParams {

    public FakeAddSubscriptionParams() {
        setApplicationName("test-application");
        setName("rest-product");
        setVersion("v1");
        setProvider("admin");
    }

}
