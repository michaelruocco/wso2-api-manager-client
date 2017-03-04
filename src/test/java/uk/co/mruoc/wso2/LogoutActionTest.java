package uk.co.mruoc.wso2;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LogoutActionTest {

    private static final String URL = "logout-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final LogoutUrlBuilder urlBuilder = mock(LogoutUrlBuilder.class);

    private final LogoutAction action = new LogoutAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build()).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.logout();

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.logout();
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnFailure() {
        givenWillReturnFailure();

        action.logout();
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillReturnSuccess();

        assertThat(action.logout()).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("logout-success.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("logout-failure.json");
        client.cannedResponse(200, body);
    }

}
