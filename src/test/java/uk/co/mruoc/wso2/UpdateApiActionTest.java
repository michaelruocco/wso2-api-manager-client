package uk.co.mruoc.wso2;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UpdateApiActionTest {

    private static final String URL = "update-api-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final UpdateApiUrlBuilder urlBuilder = mock(UpdateApiUrlBuilder.class);
    private final UpdateApiParams params = mock(UpdateApiParams.class);

    private final UpdateApiAction action = new UpdateApiAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void updateApiShouldCallCorrectUrl() {
        givenWillSucceed();

        action.updateApi(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.updateApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnFailure() {
        givenWillFail();

        action.updateApi(params);
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillSucceed();

        assertThat(action.updateApi(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillFail() {
        String body = responseLoader.load("update-api-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillSucceed() {
        String body = responseLoader.load("update-api-success.json");
        client.cannedResponse(200, body);
    }

}
