package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListAllActionTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";
    private static final String LIST_ALL_URL = "list-all-url";

    private final FileLoader fileLoader = new FileLoader();
    private final FakeHttpClient httpClient = new FakeHttpClient();
    private final ListAllUrlBuilder listAllUrlBuilder = new StubListAllUrlBuilder(LIST_ALL_URL);
    private final ListAllAction action = new ListAllAction(httpClient, listAllUrlBuilder);

    @Test
    public void listAllShouldCallCorrectUrl() {
        givenWillReturnEmptySuccess();

        action.listAllApis();

        assertThat(httpClient.lastRequestUri()).isEqualTo(LIST_ALL_URL);
    }

    @Test
    public void listAllShouldReturnEmptyListIfNoApisDeployed() {
        givenWillReturnEmptySuccess();

        List<ApiSummary> apiSummaries = action.listAllApis();

        assertThat(apiSummaries.size()).isEqualTo(0);
    }

    @Test(expected = ApiPublisherException.class)
    public void listAllShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.listAllApis();
    }

    @Test
    public void listAllShouldReturnApiSummariesIfMultipleApisDeployed() {
        givenWillReturnMultipleSuccess();

        List<ApiSummary> apiSummaries = action.listAllApis();

        assertThat(apiSummaries.size()).isEqualTo(2);
        assertThat(apiSummaries.get(0)).isEqualToComparingFieldByField(new AdminServicesApiSummary());
        assertThat(apiSummaries.get(1)).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

    private void givenWillReturnNon200() {
        httpClient.cannedResponse(500, "");
    }

    private void givenWillReturnEmptySuccess() {
        String body = load("list-api-empty-success.json");
        httpClient.cannedResponse(200, body);
    }

    private void givenWillReturnMultipleSuccess() {
        String body = load("list-api-multiple-success.json");
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
