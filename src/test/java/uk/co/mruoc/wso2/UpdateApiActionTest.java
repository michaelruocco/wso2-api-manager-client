package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class UpdateApiActionTest {

    private static final String URL = "update-api-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final UpdateApiUrlBuilder urlBuilder = new StubUpdateApiUrlBuilder(URL);
    private final UpdateApiAction action = new UpdateApiAction(client, urlBuilder);

    private final UpdateApiParams params = mock(UpdateApiParams.class);

    @Test
    public void updateApiShouldCallCorrectUrl() {
        givenUpdateApiWillSucceed();

        action.updateApi(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void updateApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.updateApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void updateApiShouldThrowExceptionOnUpdateApiFailure() {
        givenUpdateApiWillFail();

        action.updateApi(params);
    }

    @Test
    public void updateApiShouldReturnTrueOnUpdateApiSuccess() {
        givenUpdateApiWillSucceed();

        assertThat(action.updateApi(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenUpdateApiWillFail() {
        String body = responseLoader.load("update-api-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenUpdateApiWillSucceed() {
        String body = responseLoader.load("update-api-success.json");
        client.cannedResponse(200, body);
    }

}
