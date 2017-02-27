package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutActionTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String URL = "logout-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LogoutUrlBuilder logoutUrlBuilder = new StubLogoutUrlBuilder(URL);
    private final LogoutAction action = new LogoutAction(httpClient, logoutUrlBuilder);

    @Test
    public void logoutShouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.logout();

        assertThat(httpClient.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void logoutShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.logout();
    }

    @Test(expected = ApiPublisherException.class)
    public void logoutShouldThrowExceptionOnLogoutFailure() {
        givenWillReturnFailure();

        action.logout();
    }

    @Test
    public void logoutShouldReturnTrueOnLogoutSuccess() {
        givenWillReturnSuccess();

        assertThat(action.logout()).isTrue();
    }

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnSuccess() {
        String body = load("logout-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnFailure() {
        String body = load("logout-failure.json");
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
