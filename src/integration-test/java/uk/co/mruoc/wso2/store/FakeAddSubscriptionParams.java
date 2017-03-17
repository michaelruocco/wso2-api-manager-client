package uk.co.mruoc.wso2.store;

public class FakeAddSubscriptionParams extends DefaultAddSubscriptionParams {

    public FakeAddSubscriptionParams() {
        setApplicationName("test-application");
        setName("rest-product");
        setVersion("v1");
        setProvider("admin");
    }

}
