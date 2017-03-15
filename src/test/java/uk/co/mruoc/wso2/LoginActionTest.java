package uk.co.mruoc.wso2;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LoginActionTest {

    private static final String URL = "login-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final LoginUrlBuilder urlBuilder = mock(PublisherLoginUrlBuilder.class);
    private final Credentials credentials = mock(Credentials.class);
    private final ResponseErrorChecker errorChecker = new PublisherResponseErrorChecker();

    private final LoginAction action = new LoginAction(httpClient, urlBuilder, errorChecker);

    @Before
    public void setUp() {
        given(urlBuilder.build(credentials)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.login(credentials);

        assertThat(httpClient.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfError() {
        givenWillReturnError();

        action.login(credentials);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnFailure() {
        givenWillReturnFailure();

        action.login(credentials);
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillReturnSuccess();

        assertThat(action.login(credentials)).isTrue();
    }

    private void givenWillReturnError() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("login-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("login-failure.json");
        httpClient.cannedResponse(200, body);
    }

}
