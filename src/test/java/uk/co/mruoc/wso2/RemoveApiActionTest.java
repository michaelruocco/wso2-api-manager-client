package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class RemoveApiActionTest {

    private static final String URL = "remove-api-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final RemoveApiUrlBuilder urlBuilder = new StubRemoveApiUrlBuilder(URL);
    private final RemoveApiAction action = new RemoveApiAction(httpClient, urlBuilder);

    private final SelectApiParams params = mock(SelectApiParams.class);

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.removeApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnRemoveApiFailure() {
        givenWillReturnRemoveApiFailure();

        action.removeApi(params);
    }

    @Test
    public void shouldReturnTrueOnRemoveApiSuccess() {
        givenWillReturnRemoveApiSuccess();

        assertThat(action.removeApi(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnRemoveApiFailure() {
        String body = responseLoader.load("remove-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnRemoveApiSuccess() {
        String body = responseLoader.load("remove-api-success.json");
        httpClient.cannedResponse(200, body);
    }

}
