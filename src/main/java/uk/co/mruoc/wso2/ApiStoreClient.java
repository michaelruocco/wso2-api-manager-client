package uk.co.mruoc.wso2;

public interface ApiStoreClient {

    boolean login(Credentials credentials);

    boolean logout();

    boolean addApplication(AddApplicationParams params);

    boolean removeApplication(String name);

    boolean addSubscription(AddSubscriptionParams params);

    boolean removeSubscription(RemoveSubscriptionParams params);

    ApplicationKey generateApplicationKey(GenerateApplicationKeyParams params);

}
