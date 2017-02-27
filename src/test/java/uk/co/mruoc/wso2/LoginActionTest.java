package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LoginActionTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String URL = "login-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LoginUrlBuilder loginUrlBuilder = new StubLoginUrlBuilder(URL);
    private final LoginAction client = new LoginAction(httpClient, loginUrlBuilder);

    private final Credentials credentials = mock(Credentials.class);

    @Test
    public void loginShouldCallCorrectUrl() {
        givenWillReturnSuccess();

        client.login(credentials);

        assertThat(httpClient.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void loginShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        client.login(credentials);
    }

    @Test(expected = ApiPublisherException.class)
    public void loginShouldThrowExceptionOnLoginFailureResponse() {
        givenWillReturnFailure();

        client.login(credentials);
    }

    @Test
    public void loginShouldReturnTrueOnLoginSuccessResponse() {
        givenWillReturnSuccess();

        assertThat(client.login(credentials)).isTrue();
    }

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnSuccess() {
        String body = load("login-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnFailure() {
        String body = load("login-failure.json");
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
