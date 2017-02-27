package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GetApiActionTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String GET_API_URL = "get-api-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final GetApiUrlBuilder getApiUrlBuilder = new StubGetApiUrlBuilder(GET_API_URL);
    private final GetApiAction action = new GetApiAction(httpClient, getApiUrlBuilder);

    private final SelectApiParams selectParams = mock(SelectApiParams.class);

    @Test
    public void getApiShouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.getApi(selectParams);

        assertThat(httpClient.lastRequestUri()).isEqualTo(GET_API_URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.getApi(selectParams);
    }

    @Test(expected = ApiPublisherException.class)
    public void getApiShouldThrowExceptionIfApiNotFound() {
        givenWillReturnFailure();

        action.getApi(selectParams);
    }

    @Test
    public void getApiShouldReturnApiIfExists() {
        givenWillReturnSuccess();

        Api api = action.getApi(selectParams);

        assertThat(api).isEqualToComparingFieldByField(new RestProductApi());
    }

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = load("get-api-failure.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = load("get-api-success.json");
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
