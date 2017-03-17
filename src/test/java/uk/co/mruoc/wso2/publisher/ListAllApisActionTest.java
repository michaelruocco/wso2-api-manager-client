package uk.co.mruoc.wso2.publisher;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListAllApisActionTest {

    private static final String URL = "list-all-url";

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final ListAllApisUrlBuilder urlBuilder = mock(ListAllApisUrlBuilder.class);
    private final ListAllApisAction action = new ListAllApisAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build()).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnEmptySuccess();

        action.listAllApis();

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test
    public void shouldReturnEmptyListIfNoApisDeployed() {
        givenWillReturnEmptySuccess();

        List<ApiSummary> apiSummaries = action.listAllApis();

        assertThat(apiSummaries.size()).isEqualTo(0);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.listAllApis();
    }

    @Test
    public void shouldReturnApiSummariesIfMultipleApisDeployed() {
        givenWillReturnMultipleSuccess();

        List<ApiSummary> apiSummaries = action.listAllApis();

        assertThat(apiSummaries.size()).isEqualTo(2);
        assertThat(apiSummaries.get(0)).isEqualToComparingFieldByField(new AdminServicesApiSummary());
        assertThat(apiSummaries.get(1)).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnEmptySuccess() {
        String body = responseLoader.load("list-api-empty-success.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnMultipleSuccess() {
        String body = responseLoader.load("list-api-multiple-success.json");
        client.cannedResponse(200, body);
    }

}
