package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;
import uk.co.mruoc.wso2.*;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationAction;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationParams;
import uk.co.mruoc.wso2.store.addsubscription.AddSubscriptionAction;
import uk.co.mruoc.wso2.store.addsubscription.AddSubscriptionParams;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyAction;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyParams;
import uk.co.mruoc.wso2.store.getsubscription.ApiSubscription;
import uk.co.mruoc.wso2.store.getsubscription.GetSubscriptionsAction;
import uk.co.mruoc.wso2.store.listallapplications.ApiApplication;
import uk.co.mruoc.wso2.store.listallapplications.ListAllApplicationsAction;
import uk.co.mruoc.wso2.store.removeapplication.RemoveApplicationAction;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionAction;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionParams;

import java.util.List;

public class DefaultApiStoreClient implements ApiStoreClient {

    private final LoginAction loginAction;
    private final LogoutAction logoutAction;
    private final AddApplicationAction addApplicationAction;
    private final ListAllApplicationsAction listAllApplicationsAction;
    private final RemoveApplicationAction removeApplicationAction;
    private final AddSubscriptionAction addSubscriptionAction;
    private final RemoveSubscriptionAction removeSubscriptionAction;
    private final GetSubscriptionsAction getSubscriptionsAction;
    private final GenerateApplicationKeyAction generateApplicationKeyAction;

    public DefaultApiStoreClient(String hostUrl) {
        this(new SimpleHttpClient(), hostUrl);
    }

    public DefaultApiStoreClient(HttpClient client, String hostUrl) {
        this(new LoginAction(client, new StoreLoginUrlBuilder(hostUrl), new StoreResponseErrorChecker()),
                new LogoutAction(client, new StoreLogoutUrlBuilder(hostUrl), new StoreResponseErrorChecker()),
                new AddApplicationAction(client, hostUrl),
                new ListAllApplicationsAction(client, hostUrl),
                new RemoveApplicationAction(client, hostUrl),
                new AddSubscriptionAction(client, hostUrl),
                new RemoveSubscriptionAction(client, hostUrl),
                new GetSubscriptionsAction(client, hostUrl),
                new GenerateApplicationKeyAction(client, hostUrl));
    }

    public DefaultApiStoreClient(LoginAction loginAction,
                                 LogoutAction logoutAction,
                                 AddApplicationAction addApplicationAction,
                                 ListAllApplicationsAction listAllApplicationsAction,
                                 RemoveApplicationAction removeApplicationAction,
                                 AddSubscriptionAction addSubscriptionAction,
                                 RemoveSubscriptionAction removeSubscriptionAction,
                                 GetSubscriptionsAction getSubscriptionsAction,
                                 GenerateApplicationKeyAction generateApplicationKeyAction) {
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
        this.addApplicationAction = addApplicationAction;
        this.listAllApplicationsAction = listAllApplicationsAction;
        this.removeApplicationAction = removeApplicationAction;
        this.addSubscriptionAction = addSubscriptionAction;
        this.removeSubscriptionAction = removeSubscriptionAction;
        this.getSubscriptionsAction = getSubscriptionsAction;
        this.generateApplicationKeyAction = generateApplicationKeyAction;
    }

    @Override
    public boolean login(Credentials credentials) {
        return loginAction.login(credentials);
    }

    @Override
    public boolean logout() {
        return logoutAction.logout();
    }

    @Override
    public boolean addApplication(AddApplicationParams params) {
        return addApplicationAction.addApplication(params);
    }

    @Override
    public boolean removeApplication(String name) {
        return removeApplicationAction.removeApplication(name);
    }

    @Override
    public List<ApiApplication> listAllApplications() {
        return listAllApplicationsAction.listAllApplications();
    }

    @Override
    public boolean addSubscription(AddSubscriptionParams params) {
        return addSubscriptionAction.addSubscription(params);
    }

    @Override
    public boolean removeSubscription(RemoveSubscriptionParams params) {
        return removeSubscriptionAction.removeSubscription(params);
    }

    @Override
    public List<ApiSubscription> getSubscriptionsByApi(SelectApiParams params) {
        return getSubscriptionsAction.getSubscriptions(params);
    }

    @Override
    public ApplicationKey generateApplicationKey(GenerateApplicationKeyParams params) {
        return generateApplicationKeyAction.generateKey(params);
    }

}
