package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;
import uk.co.mruoc.wso2.*;

import java.util.List;

public class DefaultApiStoreClient implements ApiStoreClient {

    private final LoginAction loginAction;
    private final LogoutAction logoutAction;
    private final AddApplicationAction addApplicationAction;
    private final ListAllApplicationsAction listAllApplicationsAction;
    private final RemoveApplicationAction removeApplicationAction;

    public DefaultApiStoreClient(String hostUrl) {
        this(new SimpleHttpClient(), hostUrl);
    }

    public DefaultApiStoreClient(HttpClient client, String hostUrl) {
        this(new LoginAction(client, new StoreLoginUrlBuilder(hostUrl), new StoreResponseErrorChecker()),
                new LogoutAction(client, new StoreLogoutUrlBuilder(hostUrl), new StoreResponseErrorChecker()),
                new AddApplicationAction(client, hostUrl),
                new ListAllApplicationsAction(client, hostUrl),
                new RemoveApplicationAction(client, hostUrl));
    }

    public DefaultApiStoreClient(LoginAction loginAction,
                                 LogoutAction logoutAction,
                                 AddApplicationAction addApplicationAction,
                                 ListAllApplicationsAction listAllApplicationsAction,
                                 RemoveApplicationAction removeApplicationAction) {
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
        this.addApplicationAction = addApplicationAction;
        this.listAllApplicationsAction = listAllApplicationsAction;
        this.removeApplicationAction = removeApplicationAction;
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
        return false;
    }

    @Override
    public boolean removeSubscription(RemoveSubscriptionParams params) {
        return false;
    }

    @Override
    public List<ApiSubscription> getSubscriptionsByApi(SelectApiParams params) {
        return null;
    }

    @Override
    public ApplicationKey generateApplicationKey(GenerateApplicationKeyParams params) {
        return null;
    }

}
