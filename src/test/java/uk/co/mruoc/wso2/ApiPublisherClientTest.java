package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.DefaultGetApiParams.DefaultGetApiParamsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPublisherClientTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String HOST_URL = "https://localhost:9443";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final DefaultApiPublisherClient client = new DefaultApiPublisherClient(HOST_URL, httpClient);
    private final Credentials credentials = new Credentials("admin", "admin");
    private final GetApiParams getApiParams = new DefaultGetApiParamsBuilder()
            .setName("rest-product")
            .setVersion("v1")
            .setProvider("admin")
            .build();

    @Test
    public void loginShouldCallCorrectUrl() {
        String expectedUrl = buildExpectedLoginUrl(credentials);
        givenWillReturnLoginSuccess();

        client.login(credentials);

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
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
        String expectedUrl = HOST_URL + "/publisher/site/blocks/user/login/ajax/login.jag?action=logout";
        givenWillReturnLogoutSuccess();

        client.logout();

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
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
        String expectedUrl = HOST_URL + "/publisher/site/blocks/listing/ajax/item-list.jag?action=getAllAPIs";
        givenWillReturnListApiEmptySuccess();

        client.listAll();

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
    }

    @Test(expected = ApiPublisherException.class)
    public void listAllShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.listAll();
    }

    @Test
    public void listAllShouldReturnEmptyListIfNoApisDeployed() {
        givenWillReturnListApiEmptySuccess();

        List<ApiSummary> apiSummaries = client.listAll();

        assertThat(apiSummaries.size()).isEqualTo(0);
    }

    @Test
    public void listAllShouldReturnApiSummariesIfMultipleApisDeployed() {
        givenWillReturnListApiMultipleSuccess();

        List<ApiSummary> apiSummaries = client.listAll();

        assertThat(apiSummaries.size()).isEqualTo(2);
        assertThat(apiSummaries.get(0)).isEqualToComparingFieldByField(new AdminServicesApiSummary());
        assertThat(apiSummaries.get(1)).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

    @Test
    public void getApiShouldCallCorrectUrl() {
        String expectedUrl = buildExpectedGetApiUrl(getApiParams);
        givenWillReturnListApiEmptySuccess();

        client.getApi(getApiParams);

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
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

    private String buildExpectedLoginUrl(Credentials credentials) {
        String url = HOST_URL + "/publisher/site/blocks/user/login/ajax/login.jag?action=login&username=%s&password=%s";
        return String.format(url, credentials.getUsername(), credentials.getPassword());
    }

    private String buildExpectedGetApiUrl(GetApiParams params) {
        String url = HOST_URL + "/publisher/site/blocks/listing/ajax/item-list.jag?action=getAPI&name=%s&version=%s&provider=%s";
        return String.format(url, params.getName(), params.getVersion(), params.getProvider());
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

    private String load(String filename) {
        String path = buildPath(filename);
        return fileLoader.loadContent(path);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
