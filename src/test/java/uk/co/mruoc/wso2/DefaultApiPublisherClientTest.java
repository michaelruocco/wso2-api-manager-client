package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultApiPublisherClientTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String API_NAME = "api-name";
    private static final String UPDATE_API_URL = "update-api-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LoginAction loginAction = mock(LoginAction.class);
    private final LogoutAction logoutAction = mock(LogoutAction.class);
    private final ListAllAction listAllAction = mock(ListAllAction.class);
    private final GetApiAction getAction = mock(GetApiAction.class);
    private final AddApiAction addAction = mock(AddApiAction.class);
    private final ApiExistsAction existsAction = mock(ApiExistsAction.class);
    private final UpdateApiUrlBuilder updateApiUrlBuilder = new StubUpdateApiUrlBuilder(UPDATE_API_URL);
    private final RemoveApiAction removeAction = mock(RemoveApiAction.class);
    private final DefaultApiPublisherClient client = new DefaultApiPublisherClient(httpClient, loginAction, logoutAction, listAllAction, getAction, addAction, existsAction, updateApiUrlBuilder, removeAction);

    private final Credentials credentials = mock(Credentials.class);
    private final SelectApiParams selectParams = mock(SelectApiParams.class);
    private final AddApiParams addParams = mock(AddApiParams.class);
    private final UpdateApiParams updateApiParams = mock(UpdateApiParams.class);
    private final SelectApiParams removeParams = mock(UpdateApiParams.class);
    private final Throwable apiPublisherException = mock(ApiPublisherException.class);

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

    @Test
    public void updateApiShouldCallCorrectUrl() {
        givenWillReturnUpdateApiSuccess();

        client.updateApi(updateApiParams);

        assertThat(httpClient.lastRequestUri()).isEqualTo(UPDATE_API_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void updateApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.updateApi(updateApiParams);
    }

    @Test(expected = ApiPublisherException.class)
    public void updateApiShouldThrowExceptionOnUpdateApiFailure() {
        givenWillReturnUpdateApiFailure();

        client.updateApi(updateApiParams);
    }

    @Test
    public void updateApiShouldReturnTrueOnUpdateApiSuccess() {
        givenWillReturnUpdateApiSuccess();

        assertThat(client.updateApi(updateApiParams)).isTrue();
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

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
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
        given(listAllAction.listAllApis()).willThrow(apiPublisherException);
    }

    private List<ApiSummary> givenListAllWillReturnSummaries() {
        List<ApiSummary> summaries = buildSummaries();
        given(listAllAction.listAllApis()).willReturn(summaries);
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

    private void givenWillReturnUpdateApiFailure() {
        String body = load("update-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnUpdateApiSuccess() {
        String body = load("update-api-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenRemoveApiWillFail() {
        given(removeAction.removeApi(removeParams)).willThrow(apiPublisherException);
    }

    private void givenRemoveApiWillSucceed() {
        given(removeAction.removeApi(removeParams)).willReturn(true);
    }

    private String load(String filename) {
        String path = buildPath(filename);
        return fileLoader.loadContent(path);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
