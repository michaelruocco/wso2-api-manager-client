package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultApiStoreClientTest {

    private final LoginAction loginAction = mock(LoginAction.class);
    private final LogoutAction logoutAction = mock(LogoutAction.class);
    private final AddApplicationAction addApplicationAction = mock(AddApplicationAction.class);

    private final Credentials credentials = mock(Credentials.class);
    private final AddApplicationParams addApplicationParams = mock(AddApplicationParams.class);

    private final Throwable apiStoreException = mock(ApiStoreException.class);

    private final ApiStoreClient client = new DefaultApiStoreClient(loginAction,
            logoutAction,
            addApplicationAction);

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
    public void addApplicationShouldThrowExceptionIfOnFailure() {
        givenAddApplicationWillFail();

        client.addApplication(addApplicationParams);
    }

    @Test
    public void addApplicationShouldReturnTrueOnSuccess() {
        givenAddApplicationWillSucceed();

        assertThat(client.addApplication(addApplicationParams)).isTrue();
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

}
