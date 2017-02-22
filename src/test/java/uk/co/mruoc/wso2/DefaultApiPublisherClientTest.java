package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DefaultApiPublisherClientTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String LOGIN_URL = "login-url";
    private static final String LOGOUT_URL = "logout-url";
    private static final String LIST_ALL_URL = "list-all-url";
    private static final String GET_API_URL = "get-api-url";
    private static final String ADD_API_URL = "add-api-url";
    private static final String API_EXISTS_URL = "api-apiExists-url";
    private static final String UPDATE_API_URL = "update-api-url";
    private static final String REMOVE_API_URL = "remove-api-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LoginUrlBuilder loginUrlBuilder = new StubLoginUrlBuilder(LOGIN_URL);
    private final LogoutUrlBuilder logoutUrlBuilder = new StubLogoutUrlBuilder(LOGOUT_URL);
    private final ListAllUrlBuilder listAllUrlBuilder = new StubListAllUrlBuilder(LIST_ALL_URL);
    private final GetApiUrlBuilder getApiUrlBuilder = new StubGetApiUrlBuilder(GET_API_URL);
    private final AddApiUrlBuilder addApiUrlBuilder = new StubAddApiUrlBuilder(ADD_API_URL);
    private final ApiExistsUrlBuilder apiExistsUrlBuilder = new StubApiExistsUrlBuilder(API_EXISTS_URL);
    private final UpdateApiUrlBuilder updateApiUrlBuilder = new StubUpdateApiUrlBuilder(UPDATE_API_URL);
    private final RemoveApiUrlBuilder removeApiUrlBuilder = new StubRemoveApiUrlBuilder(REMOVE_API_URL);
    private final DefaultApiPublisherClient client = new DefaultApiPublisherClient(httpClient, loginUrlBuilder, logoutUrlBuilder, listAllUrlBuilder, getApiUrlBuilder, addApiUrlBuilder, apiExistsUrlBuilder, updateApiUrlBuilder, removeApiUrlBuilder);

    private final Credentials credentials = mock(Credentials.class);
    private final SelectApiParams getApiParams = mock(SelectApiParams.class);
    private final AddApiParams addApiParams = mock(AddApiParams.class);
    private final UpdateApiParams updateApiParams = mock(UpdateApiParams.class);
    private final SelectApiParams removeApiParams = mock(UpdateApiParams.class);

    @Test
    public void loginShouldCallCorrectUrl() {
        givenWillReturnLoginSuccess();

        client.login(credentials);

        assertThat(httpClient.lastRequestUri()).isEqualTo(LOGIN_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void loginShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.login(credentials);
    }

    @Test(expected = ApiPublisherException.class)
    public void loginShouldThrowExceptionOnLoginFailureResponse() {
        givenWillReturnLoginFailure();

        client.login(credentials);
    }

    @Test
    public void loginShouldReturnTrueOnLoginSuccessResponse() {
        givenWillReturnLoginSuccess();

        assertThat(client.login(credentials)).isTrue();
    }

    @Test
    public void logoutShouldCallCorrectUrl() {
        givenWillReturnLogoutSuccess();

        client.logout();

        assertThat(httpClient.lastRequestUri()).isEqualTo(LOGOUT_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void logoutShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.logout();
    }

    @Test(expected = ApiPublisherException.class)
    public void logoutShouldThrowExceptionOnLogoutFailure() {
        givenWillReturnLogoutFailure();

        client.logout();
    }

    @Test
    public void logoutShouldReturnTrueOnLogoutSuccess() {
        givenWillReturnLogoutSuccess();

        assertThat(client.logout()).isTrue();
    }

    @Test
    public void listAllShouldCallCorrectUrl() {
        givenWillReturnListApiEmptySuccess();

        client.listAllApis();

        assertThat(httpClient.lastRequestUri()).isEqualTo(LIST_ALL_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void listAllShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.listAllApis();
    }

    @Test
    public void listAllShouldReturnEmptyListIfNoApisDeployed() {
        givenWillReturnListApiEmptySuccess();

        List<ApiSummary> apiSummaries = client.listAllApis();

        assertThat(apiSummaries.size()).isEqualTo(0);
    }

    @Test
    public void listAllShouldReturnApiSummariesIfMultipleApisDeployed() {
        givenWillReturnListApiMultipleSuccess();

        List<ApiSummary> apiSummaries = client.listAllApis();

        assertThat(apiSummaries.size()).isEqualTo(2);
        assertThat(apiSummaries.get(0)).isEqualToComparingFieldByField(new AdminServicesApiSummary());
        assertThat(apiSummaries.get(1)).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

    @Test
    public void getApiShouldCallCorrectUrl() {
        givenWillReturnListApiEmptySuccess();

        client.getApi(getApiParams);

        assertThat(httpClient.lastRequestUri()).isEqualTo(GET_API_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.getApi(getApiParams);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfApiNotFound() {
        givenWillReturnGetApiFailure();

        client.getApi(getApiParams);
    }

    @Test
    public void getApiShouldReturnApiIfExists() {
        givenWillReturnGetApiSuccess();

        Api api = client.getApi(getApiParams);

        assertThat(api).isEqualToComparingFieldByField(new RestProductApi());
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

    private void givenWillReturnLoginSuccess() {
        String body = load("login-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnLoginFailure() {
        String body = load("login-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnLogoutSuccess() {
        String body = load("logout-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnLogoutFailure() {
        String body = load("logout-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnListApiEmptySuccess() {
        String body = load("list-api-empty-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnListApiMultipleSuccess() {
        String body = load("list-api-multiple-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnGetApiFailure() {
        String body = load("get-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnGetApiSuccess() {
        String body = load("get-api-success.json");
        httpClient.cannedResponse(200, body);
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
