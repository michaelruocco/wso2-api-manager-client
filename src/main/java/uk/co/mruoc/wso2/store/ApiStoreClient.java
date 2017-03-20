package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.Credentials;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationParams;
import uk.co.mruoc.wso2.store.addsubscription.AddSubscriptionParams;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyParams;
import uk.co.mruoc.wso2.store.getsubscription.ApiSubscription;
import uk.co.mruoc.wso2.store.listallapplications.ApiApplication;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionParams;

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
