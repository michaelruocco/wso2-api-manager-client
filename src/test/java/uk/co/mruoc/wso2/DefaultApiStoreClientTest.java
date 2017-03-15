package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultApiStoreClientTest {

    private final LoginAction loginAction = mock(LoginAction.class);
    private final LogoutAction logoutAction = mock(LogoutAction.class);
    private final AddApplicationAction addApplicationAction = mock(AddApplicationAction.class);
    private final ListAllApplicationsAction listAllApplicationsAction = mock(ListAllApplicationsAction.class);

    private final Credentials credentials = mock(Credentials.class);
    private final AddApplicationParams addApplicationParams = mock(AddApplicationParams.class);

    private final Throwable apiStoreException = mock(ApiStoreException.class);

    private final ApiStoreClient client = new DefaultApiStoreClient(loginAction,
            logoutAction,
            addApplicationAction,
            listAllApplicationsAction);

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

}
