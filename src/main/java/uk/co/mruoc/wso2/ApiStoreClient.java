package uk.co.mruoc.wso2;

public interface ApiStoreClient {

    boolean login(Credentials credentials);

    boolean logout();

    boolean addSubscription(AddSubscriptionParams params);

    ApplicationKey generateApplicationKey(GenerateApplicationKeyParams params);

}
