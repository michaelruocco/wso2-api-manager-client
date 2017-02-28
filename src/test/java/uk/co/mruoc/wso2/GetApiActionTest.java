package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GetApiActionTest {

    private static final String URL = "get-api-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final GetApiUrlBuilder urlBuilder = new StubGetApiUrlBuilder(URL);
    private final GetApiAction action = new GetApiAction(client, urlBuilder);

    private final SelectApiParams params = mock(SelectApiParams.class);

    @Test
    public void getApiShouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.getApi(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.getApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfApiNotFound() {
        givenWillReturnFailure();

        action.getApi(params);
    }

    @Test
    public void getApiShouldReturnApiIfExists() {
        givenWillReturnSuccess();

        Api api = action.getApi(params);

        assertThat(api).isEqualToComparingFieldByField(new RestProductApi());
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("get-api-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("get-api-success.json");
        client.cannedResponse(200, body);
    }

}
