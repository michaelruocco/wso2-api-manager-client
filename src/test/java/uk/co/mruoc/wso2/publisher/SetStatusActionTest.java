package uk.co.mruoc.wso2.publisher;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SetStatusActionTest {

    private static final String URL = "set-status-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final SetStatusUrlBuilder urlBuilder = mock(SetStatusUrlBuilder.class);
    private final SetStatusAction action = new SetStatusAction(client, urlBuilder);

    private final SetStatusParams params = mock(SetStatusParams.class);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.setStatus(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.setStatus(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnFailure() {
        givenWillReturnFailure();

        action.setStatus(params);
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillReturnSuccess();

        assertThat(action.setStatus(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("set-status-success.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("set-status-failure.json");
        client.cannedResponse(200, body);
    }

}
