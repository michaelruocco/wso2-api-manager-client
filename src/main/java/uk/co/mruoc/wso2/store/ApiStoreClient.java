package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.Credentials;
import uk.co.mruoc.wso2.SelectApiParams;

import java.util.List;

public interface ApiStoreClient {

    boolean login(Credentials credentials);

    boolean logout();

    boolean addApplication(AddApplicationParams params);

    boolean removeApplication(String name);

    List<ApiApplication> listAllApplications();

    boolean addSubscription(AddSubscriptionParams params);

    boolean removeSubscription(RemoveSubscriptionParams params);

    List<ApiSubscription> getSubscriptionsByApi(SelectApiParams params);

    ApplicationKey generateApplicationKey(GenerateApplicationKeyParams params);

}
