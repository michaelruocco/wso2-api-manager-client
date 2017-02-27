package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LoginActionTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String LOGIN_URL = "login-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LoginUrlBuilder loginUrlBuilder = new StubLoginUrlBuilder(LOGIN_URL);
    private final LoginAction client = new LoginAction(httpClient, loginUrlBuilder);

    private final Credentials credentials = mock(Credentials.class);

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
