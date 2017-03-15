package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultApiPublisherClientTest {

    private static final String API_NAME = "api-name";

    private final LoginAction loginAction = mock(LoginAction.class);
    private final LogoutAction logoutAction = mock(LogoutAction.class);
    private final ListAllApisAction listAllApisAction = mock(ListAllApisAction.class);
    private final GetApiAction getAction = mock(GetApiAction.class);
    private final AddApiAction addAction = mock(AddApiAction.class);
    private final ApiExistsAction existsAction = mock(ApiExistsAction.class);
    private final UpdateApiAction updateAction = mock(UpdateApiAction.class);
    private final RemoveApiAction removeAction = mock(RemoveApiAction.class);
    private final SetStatusAction setStatusAction = mock(SetStatusAction.class);

    private final Credentials credentials = mock(Credentials.class);
    private final SelectApiParams selectParams = mock(SelectApiParams.class);
    private final AddApiParams addParams = mock(AddApiParams.class);
    private final UpdateApiParams updateParams = mock(UpdateApiParams.class);
    private final SelectApiParams removeParams = mock(UpdateApiParams.class);
    private final SetStatusParams setStatusParams = mock(SetStatusParams.class);
    private final Throwable apiPublisherException = mock(ApiPublisherException.class);

    private final ApiPublisherClient client = new DefaultApiPublisherClient(loginAction,
            logoutAction,
            listAllApisAction,
            getAction,
            addAction,
            existsAction,
            updateAction,
            removeAction,
            setStatusAction);

    @Test(expected = ApiPublisherException.class)
    public void loginShouldThrowExceptionIfLoginFails() {
        givenLoginWillFail();

        client.login(credentials);
    }

    @Test
    public void loginShouldReturnTrueOnSuccess() {
        givenLoginWillSucceed();

        assertThat(client.login(credentials)).isTrue();
    }

    @Test(expected = ApiPublisherException.class)
    public void logoutShouldThrowExceptionIfLogoutFails() {
        givenLogoutWillFail();

        client.logout();
    }

    @Test
    public void logoutShouldReturnTrueOnSuccess() {
        givenLogoutWillSucceed();

        assertThat(client.logout()).isTrue();
    }

    @Test(expected = ApiPublisherException.class)
    public void listAllShouldThrowExceptionIfListAllFails() {
        givenListAllWillFail();

        client.listAllApis();
    }

    @Test
    public void listAllShouldReturnApiSummariesIfApisDeployed() {
        List<ApiSummary> expectedSummaries = givenListAllWillReturnSummaries();

        List<ApiSummary> result = client.listAllApis();

        assertThat(result).isEqualTo(expectedSummaries);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfGetApiFails() {
        givenGetApiWillFail();

        client.getApi(selectParams);
    }

    @Test
    public void getApiShouldReturnApiIfApiDeployed() {
        Api expectedApi = givenGetApiWillReturnApi();

        Api result = client.getApi(selectParams);

        assertThat(result).isEqualToComparingFieldByField(expectedApi);
    }

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionIfAddApiFails() {
        givenAddApiWillFail();

        client.addApi(addParams);
    }

    @Test
    public void addApiShouldReturnTrueOnAddApiSuccess() {
        givenAddApiWillSucceed();

        assertThat(client.addApi(addParams)).isTrue();
    }

    @Test(expected = ApiPublisherException.class)
    public void apiExistsShouldThrowExceptionIfApiExistsFails() {
        givenApiExistsWillFail();

        client.apiExists(API_NAME);
    }

    @Test
    public void apiExistsShouldReturnTrueIfApiExists() {
        givenApiExists();

        assertThat(client.apiExists(API_NAME)).isTrue();
    }

    @Test
    public void apiExistsShouldReturnFalseIfApiDoesNotExist() {
        givenApiDoesNotExist();

        assertThat(client.apiExists(API_NAME)).isFalse();
    }

    @Test(expected = ApiPublisherException.class)
    public void updateApiShouldThrowExceptionIfUpdateApiFails() {
        givenUpdateApiWillFail();

        client.updateApi(updateParams);
    }

    @Test
    public void updateApiShouldReturnTrueOnSuccess() {
        givenUpdateApiWillSucceed();

        assertThat(client.updateApi(updateParams)).isTrue();
    }

    @Test(expected = ApiPublisherException.class)
    public void removeApiShouldThrowExceptionOnFailure() {
        givenRemoveApiWillFail();

        client.removeApi(removeParams);
    }

    @Test
    public void removeApiShouldReturnTrueOnSuccess() {
        givenRemoveApiWillSucceed();

        assertThat(client.removeApi(removeParams)).isTrue();
    }

    @Test(expected = ApiPublisherException.class)
    public void setStatusShouldThrowExceptionIfSetStatusFails() {
        givenSetStatusWillFail();

        client.setStatus(setStatusParams);
    }

    @Test
    public void setStatusShouldReturnTrueSetStatusOnSuccess() {
        givenSetStatusWillSucceed();

        assertThat(client.setStatus(setStatusParams)).isTrue();
    }

    private void givenLoginWillFail() {
        given(loginAction.login(credentials)).willThrow(apiPublisherException);
    }

    private void givenLoginWillSucceed() {
        given(loginAction.login(credentials)).willReturn(true);
    }

    private void givenLogoutWillFail() {
        given(logoutAction.logout()).willThrow(apiPublisherException);
    }

    private void givenLogoutWillSucceed() {
        given(logoutAction.logout()).willReturn(true);
    }

    private void givenListAllWillFail() {
        given(listAllApisAction.listAllApis()).willThrow(apiPublisherException);
    }

    private List<ApiSummary> givenListAllWillReturnSummaries() {
        List<ApiSummary> summaries = buildSummaries();
        given(listAllApisAction.listAllApis()).willReturn(summaries);
        return summaries;
    }

    private List<ApiSummary> buildSummaries() {
        List<ApiSummary> summaries = new ArrayList<>();
        summaries.add(new AdminServicesApiSummary());
        summaries.add(new ColleagueApiSummary());
        return summaries;
    }

    private void givenGetApiWillFail() {
        given(getAction.getApi(selectParams)).willThrow(apiPublisherException);
    }

    private Api givenGetApiWillReturnApi() {
        Api api = new RestProductApi();
        given(getAction.getApi(selectParams)).willReturn(api);
        return api;
    }

    private void givenAddApiWillFail() {
        given(addAction.addApi(addParams)).willThrow(apiPublisherException);
    }

    private void givenAddApiWillSucceed() {
        given(addAction.addApi(addParams)).willReturn(true);
    }

    private void givenApiExistsWillFail() {
        given(existsAction.apiExists(API_NAME)).willThrow(apiPublisherException);
    }

    private void givenApiExists() {
        given(existsAction.apiExists(API_NAME)).willReturn(true);
    }

    private void givenApiDoesNotExist() {
        given(existsAction.apiExists(API_NAME)).willReturn(false);
    }

    private void givenUpdateApiWillFail() {
        given(updateAction.updateApi(updateParams)).willThrow(apiPublisherException);
    }

    private void givenUpdateApiWillSucceed() {
        given(updateAction.updateApi(updateParams)).willReturn(true);
    }

    private void givenRemoveApiWillFail() {
        given(removeAction.removeApi(removeParams)).willThrow(apiPublisherException);
    }

    private void givenRemoveApiWillSucceed() {
        given(removeAction.removeApi(removeParams)).willReturn(true);
    }

    private void givenSetStatusWillFail() {
        given(setStatusAction.setStatus(setStatusParams)).willThrow(apiPublisherException);
    }

    private void givenSetStatusWillSucceed() {
        given(setStatusAction.setStatus(setStatusParams)).willReturn(true);
    }

}
