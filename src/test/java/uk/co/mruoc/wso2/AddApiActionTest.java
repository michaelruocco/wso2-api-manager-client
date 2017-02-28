package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AddApiActionTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String ADD_API_URL = "add-api-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final AddApiUrlBuilder addApiUrlBuilder = new StubAddApiUrlBuilder(ADD_API_URL);
    private final AddApiAction action = new AddApiAction(httpClient, addApiUrlBuilder);

    private final AddApiParams addApiParams = mock(AddApiParams.class);

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.addApi(addApiParams);
    }

    @Test(expected = ApiPublisherException.class)
    public void addApiShouldThrowExceptionOnAddApiFailure() {
        givenWillReturnAddApiFailure();

        action.addApi(addApiParams);
    }

    @Test
    public void addApiShouldReturnTrueOnAddApiSuccess() {
        givenWillReturnAddApiSuccess();

        assertThat(action.addApi(addApiParams)).isTrue();
    }

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnAddApiFailure() {
        String body = load("add-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnAddApiSuccess() {
        String body = load("add-api-success.json");
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
