package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.util.List;

public class DefaultApiStoreClient implements ApiStoreClient {

    private final LoginAction loginAction;
    private final LogoutAction logoutAction;

    public DefaultApiStoreClient(String hostUrl) {
        this(new SimpleHttpClient(), hostUrl);
    }

    public DefaultApiStoreClient(HttpClient client, String hostUrl) {
        this(new LoginAction(client, new StoreLoginUrlBuilder(hostUrl), new StoreResponseErrorChecker()),
                new LogoutAction(client, new StoreLogoutUrlBuilder(hostUrl), new StoreResponseErrorChecker()));
    }
    public DefaultApiStoreClient(LoginAction loginAction, LogoutAction logoutAction) {
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
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
        return false;
    }

    @Override
    public boolean removeApplication(String name) {
        return false;
    }

    @Override
    public List<ApiApplication> listAllApplications() {
        return null;
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
