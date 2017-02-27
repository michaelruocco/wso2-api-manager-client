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
    private static final String ADD_API_URL = "add-api-url";
    private static final String API_EXISTS_URL = "api-apiExists-url";
    private static final String UPDATE_API_URL = "update-api-url";
    private static final String REMOVE_API_URL = "remove-api-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LoginAction loginAction = mock(LoginAction.class);
    private final LogoutAction logoutAction = mock(LogoutAction.class);
    private final ListAllAction listAllAction = mock(ListAllAction.class);
    private final GetApiAction getApiAction = mock(GetApiAction.class);
    private final AddApiUrlBuilder addApiUrlBuilder = new StubAddApiUrlBuilder(ADD_API_URL);
    private final ApiExistsUrlBuilder apiExistsUrlBuilder = new StubApiExistsUrlBuilder(API_EXISTS_URL);
    private final UpdateApiUrlBuilder updateApiUrlBuilder = new StubUpdateApiUrlBuilder(UPDATE_API_URL);
    private final RemoveApiUrlBuilder removeApiUrlBuilder = new StubRemoveApiUrlBuilder(REMOVE_API_URL);
    private final DefaultApiPublisherClient client = new DefaultApiPublisherClient(httpClient, loginAction, logoutAction, listAllAction, getApiAction, addApiUrlBuilder, apiExistsUrlBuilder, updateApiUrlBuilder, removeApiUrlBuilder);

    private final Credentials credentials = mock(Credentials.class);
    private final SelectApiParams selectParams = mock(SelectApiParams.class);
    private final AddApiParams addApiParams = mock(AddApiParams.class);
    private final UpdateApiParams updateApiParams = mock(UpdateApiParams.class);
    private final SelectApiParams removeApiParams = mock(UpdateApiParams.class);
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
    public void listAllShouldReturnApiSummariesIfMultipleApisDeployed() {
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
    public void getApiShouldReturnApiIfExists() {
        Api expectedApi = givenGetApiWillReturnApi();

        Api result = client.getApi(selectParams);

        assertThat(result).isEqualToComparingFieldByField(expectedApi);
    }

    @Test
    public void addApiShouldCallCorrectUrl() {
        givenWillReturnAddApiSuccess();

        client.addApi(addApiParams);

        assertThat(httpClient.lastRequestUri()).isEqualTo(ADD_API_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.addApi(addApiParams);
    }

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionOnAddApiFailure() {
        givenWillReturnAddApiFailure();

        client.addApi(addApiParams);
    }

    @Test
    public void addApiShouldReturnTrueOnAddApiSuccess() {
        givenWillReturnAddApiSuccess();

        assertThat(client.addApi(addApiParams)).isTrue();
    }

    @Test
    public void apiExistsShouldCallCorrectUrl() {
        givenWillReturnApiExistsSuccess();

        client.apiExists("name");

        assertThat(httpClient.lastRequestUri()).isEqualTo(API_EXISTS_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void apiExistsShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.apiExists("name");
    }

    @Test
    public void apiExistsShouldReturnFalseFailure() {
        givenWillReturnApiExistsFailure();

        assertThat(client.apiExists("name")).isFalse();
    }

    @Test
    public void apiExistsShouldReturnTrueOnSuccess() {
        givenWillReturnApiExistsSuccess();

        assertThat(client.apiExists("name")).isTrue();
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
    public void removeApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.removeApi(removeApiParams);
    }

    @Test(expected = ApiPublisherException.class)
    public void removeApiShouldThrowExceptionOnRemoveApiFailure() {
        givenWillReturnRemoveApiFailure();

        client.removeApi(removeApiParams);
    }

    @Test
    public void removeApiShouldReturnTrueOnRemoveApiSuccess() {
        givenWillReturnRemoveApiSuccess();

        assertThat(client.removeApi(removeApiParams)).isTrue();
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
        given(getApiAction.getApi(selectParams)).willThrow(apiPublisherException);
    }

    private Api givenGetApiWillReturnApi() {
        Api api = new RestProductApi();
        given(getApiAction.getApi(selectParams)).willReturn(api);
        return api;
    }

    private void givenWillReturnAddApiFailure() {
        String body = load("add-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnAddApiSuccess() {
        String body = load("add-api-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnApiExistsFailure() {
        String body = load("api-exists-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnApiExistsSuccess() {
        String body = load("api-exists-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnUpdateApiFailure() {
        String body = load("update-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnUpdateApiSuccess() {
        String body = load("update-api-success.json");
        httpClient.cannedResponse(200, body);
    }


    private void givenWillReturnRemoveApiFailure() {
        String body = load("remove-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnRemoveApiSuccess() {
        String body = load("remove-api-success.json");
        httpClient.cannedResponse(200, body);
    }

    private String load(String filename) {
        String path = buildPath(filename);
        return fileLoader.loadContent(path);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
