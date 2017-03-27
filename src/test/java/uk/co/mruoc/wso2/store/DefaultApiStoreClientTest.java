package uk.co.mruoc.wso2.store;

import org.junit.Test;
import uk.co.mruoc.wso2.Credentials;
import uk.co.mruoc.wso2.LoginAction;
import uk.co.mruoc.wso2.LogoutAction;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationAction;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationParams;
import uk.co.mruoc.wso2.store.addsubscription.AddSubscriptionAction;
import uk.co.mruoc.wso2.store.addsubscription.AddSubscriptionParams;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;
import uk.co.mruoc.wso2.store.generateapplicationkey.FakeApplicationKey;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyAction;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyParams;
import uk.co.mruoc.wso2.store.getsubscription.ApiSubscription;
import uk.co.mruoc.wso2.store.getsubscription.GetSubscriptionsAction;
import uk.co.mruoc.wso2.store.listallapplications.ApiApplication;
import uk.co.mruoc.wso2.store.listallapplications.DefaultApiApplication;
import uk.co.mruoc.wso2.store.listallapplications.ListAllApplicationsAction;
import uk.co.mruoc.wso2.store.removeapplication.RemoveApplicationAction;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionAction;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionParams;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultApiStoreClientTest {

    private static final String APPLICATION_NAME = "application-name";

    private final LoginAction loginAction = mock(LoginAction.class);
    private final LogoutAction logoutAction = mock(LogoutAction.class);
    private final AddApplicationAction addApplicationAction = mock(AddApplicationAction.class);
    private final ListAllApplicationsAction listAllApplicationsAction = mock(ListAllApplicationsAction.class);
    private final RemoveApplicationAction removeApplicationAction = mock(RemoveApplicationAction.class);
    private final AddSubscriptionAction addSubscriptionAction = mock(AddSubscriptionAction.class);
    private final RemoveSubscriptionAction removeSubscriptionAction = mock(RemoveSubscriptionAction.class);
    private final GetSubscriptionsAction getSubscriptionsAction = mock(GetSubscriptionsAction.class);
    private final GenerateApplicationKeyAction generateApplicationKeyAction = mock(GenerateApplicationKeyAction.class);

    private final Credentials credentials = mock(Credentials.class);
    private final AddApplicationParams addApplicationParams = mock(AddApplicationParams.class);
    private final AddSubscriptionParams addSubscriptionParams = mock(AddSubscriptionParams.class);
    private final RemoveSubscriptionParams removeSubscriptionParams = mock(RemoveSubscriptionParams.class);
    private final SelectApiParams getSubscriptionsParams = mock(SelectApiParams.class);
    private final GenerateApplicationKeyParams generateApplicationKeyParams = mock(GenerateApplicationKeyParams.class);

    private final List<ApiSubscription> subscriptions = new ArrayList<>();
    private final ApplicationKey key = new FakeApplicationKey();

    private final Throwable apiStoreException = mock(ApiStoreException.class);

    private final ApiStoreClient client = new DefaultApiStoreClient(loginAction,
            logoutAction,
            addApplicationAction,
            listAllApplicationsAction,
            removeApplicationAction,
            addSubscriptionAction,
            removeSubscriptionAction,
            getSubscriptionsAction,
            generateApplicationKeyAction);

    @Test(expected = ApiStoreException.class)
    public void loginShouldThrowExceptionOnFailure() {
        givenLoginWillFail();

        client.login(credentials);
    }

    @Test
    public void loginShouldReturnTrueOnSuccess() {
        givenLoginWillSucceed();

        assertThat(client.login(credentials)).isTrue();
    }

    @Test(expected = ApiStoreException.class)
    public void logoutShouldThrowExceptionOnFailure() {
        givenLogoutWillFail();

        client.logout();
    }

    @Test
    public void logoutShouldReturnTrueOnSuccess() {
        givenLogoutWillSucceed();

        assertThat(client.logout()).isTrue();
    }

    @Test(expected = ApiStoreException.class)
    public void addApplicationShouldThrowExceptionOnFailure() {
        givenAddApplicationWillFail();

        client.addApplication(addApplicationParams);
    }

    @Test
    public void addApplicationShouldReturnTrueOnSuccess() {
        givenAddApplicationWillSucceed();

        assertThat(client.addApplication(addApplicationParams)).isTrue();
    }

    @Test(expected = ApiStoreException.class)
    public void listAllApplicationsShouldThrowExceptionIfOnFailure() {
        givenListAllApplicationsWillFail();

        client.listAllApplications();
    }

    @Test
    public void listAllApplicationsShouldReturnTrueOnSuccess() {
        List<ApiApplication> expectedApplications = givenListAllWillReturnApplications();

        List<ApiApplication> result = client.listAllApplications();

        assertThat(result).isEqualTo(expectedApplications);
    }

    @Test(expected = ApiStoreException.class)
    public void removeApplicationShouldThrowExceptionOnFailure() {
        givenRemoveApplicationWillFail();

        client.removeApplication(APPLICATION_NAME);
    }

    @Test
    public void removeApplicationShouldReturnTrueOnSuccess() {
        givenRemoveApplicationWillSucceed();

        assertThat(client.removeApplication(APPLICATION_NAME)).isTrue();
    }

    @Test(expected = ApiStoreException.class)
    public void addSubscriptionShouldThrowExceptionOnFailure() {
        givenAddSubscriptionWillFail();

        client.addSubscription(addSubscriptionParams);
    }

    @Test
    public void addSubscriptionShouldReturnTrueOnSuccess() {
        givenAddSubscriptionWillSucceed();

        assertThat(client.addSubscription(addSubscriptionParams)).isTrue();
    }

    @Test(expected = ApiStoreException.class)
    public void removeSubscriptionShouldThrowExceptionOnFailure() {
        givenRemoveSubscriptionWillFail();

        client.removeSubscription(removeSubscriptionParams);
    }

    @Test
    public void removeSubscriptionShouldReturnTrueOnSuccess() {
        givenRemoveSubscriptionWillSucceed();

        assertThat(client.removeSubscription(removeSubscriptionParams)).isTrue();
    }

    @Test(expected = ApiStoreException.class)
    public void getSubscriptionsShouldThrowExceptionOnFailure() {
        givenGetSubscriptionsWillFail();

        client.getSubscriptionsByApi(getSubscriptionsParams);
    }

    @Test
    public void getSubscriptionsShouldReturnSubscriptionsOnSuccess() {
        givenGetSubscriptionsWillSucceed();

        assertThat(client.getSubscriptionsByApi(getSubscriptionsParams)).isEqualTo(subscriptions);
    }

    @Test(expected = ApiStoreException.class)
    public void generateKeyShouldThrowExceptionOnFailure() {
        givenGenerateApplicationKeyWillFail();

        client.generateApplicationKey(generateApplicationKeyParams);
    }

    @Test
    public void generateKeyShouldReturnKeyOnSuccess() {
        givenGenerateApplicationKeyWillSucceed();

        assertThat(client.generateApplicationKey(generateApplicationKeyParams)).isEqualTo(key);
    }

    private void givenLoginWillFail() {
        given(loginAction.login(credentials)).willThrow(apiStoreException);
    }

    private void givenLoginWillSucceed() {
        given(loginAction.login(credentials)).willReturn(true);
    }

    private void givenLogoutWillFail() {
        given(logoutAction.logout()).willThrow(apiStoreException);
    }

    private void givenLogoutWillSucceed() {
        given(logoutAction.logout()).willReturn(true);
    }

    private void givenAddApplicationWillSucceed() {
        given(addApplicationAction.addApplication(addApplicationParams)).willReturn(true);
    }

    private void givenAddApplicationWillFail() {
        given(addApplicationAction.addApplication(addApplicationParams)).willThrow(apiStoreException);
    }

    private void givenListAllApplicationsWillFail() {
        given(listAllApplicationsAction.listAllApplications()).willThrow(apiStoreException);
    }

    private List<ApiApplication> givenListAllWillReturnApplications() {
        List<ApiApplication> applications = buildApplications();
        given(listAllApplicationsAction.listAllApplications()).willReturn(applications);
        return applications;
    }

    private List<ApiApplication> buildApplications() {
        List<ApiApplication> applications = new ArrayList<>();
        applications.add(new DefaultApiApplication());
        applications.add(new DefaultApiApplication());
        return applications;
    }

    private void givenRemoveApplicationWillFail() {
        given(removeApplicationAction.removeApplication(APPLICATION_NAME)).willThrow(apiStoreException);
    }

    private void givenRemoveApplicationWillSucceed() {
        given(removeApplicationAction.removeApplication(APPLICATION_NAME)).willReturn(true);
    }

    private void givenAddSubscriptionWillFail() {
        given(addSubscriptionAction.addSubscription(addSubscriptionParams)).willThrow(apiStoreException);
    }

    private void givenAddSubscriptionWillSucceed() {
        given(addSubscriptionAction.addSubscription(addSubscriptionParams)).willReturn(true);
    }

    private void givenRemoveSubscriptionWillFail() {
        given(removeSubscriptionAction.removeSubscription(removeSubscriptionParams)).willThrow(apiStoreException);
    }

    private void givenRemoveSubscriptionWillSucceed() {
        given(removeSubscriptionAction.removeSubscription(removeSubscriptionParams)).willReturn(true);
    }

    private void givenGetSubscriptionsWillFail() {
        given(getSubscriptionsAction.getSubscriptions(getSubscriptionsParams)).willThrow(apiStoreException);
    }

    private void givenGetSubscriptionsWillSucceed() {
        given(getSubscriptionsAction.getSubscriptions(getSubscriptionsParams)).willReturn(subscriptions);
    }

    private void givenGenerateApplicationKeyWillFail() {
        given(generateApplicationKeyAction.generateKey(generateApplicationKeyParams)).willThrow(apiStoreException);
    }

    private void givenGenerateApplicationKeyWillSucceed() {
        given(generateApplicationKeyAction.generateKey(generateApplicationKeyParams)).willReturn(key);
    }

}
