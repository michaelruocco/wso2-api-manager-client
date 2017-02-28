package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AddApiActionTest {

    private static final String URL = "add-api-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final AddApiUrlBuilder urlBuilder = new StubAddApiUrlBuilder(URL);
    private final AddApiAction action = new AddApiAction(client, urlBuilder);

    private final AddApiParams params = mock(AddApiParams.class);

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.addApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionOnAddApiFailure() {
        givenWillReturnAddApiFailure();

        action.addApi(params);
    }

    @Test
    public void addApiShouldReturnTrueOnAddApiSuccess() {
        givenWillReturnAddApiSuccess();

        assertThat(action.addApi(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnAddApiFailure() {
        String body = responseLoader.load("add-api-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnAddApiSuccess() {
        String body = responseLoader.load("add-api-success.json");
        client.cannedResponse(200, body);
    }

}
