package uk.co.mruoc.wso2;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListAllApplicationsActionTest {

    private static final String URL = "list-all-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final ListAllApplicationsUrlBuilder urlBuilder = mock(ListAllApplicationsUrlBuilder.class);
    private final ListAllApplicationsAction action = new ListAllApplicationsAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build()).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnEmptySuccess();

        action.listAllApplications();

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test
    public void shouldReturnEmptyListIfNoApisDeployed() {
        givenWillReturnEmptySuccess();

        List<ApiApplication> applications = action.listAllApplications();

        assertThat(applications.size()).isEqualTo(0);
    }

    @Test(expected = ApiStoreException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.listAllApplications();
    }

    @Test
    public void shouldReturnApiSummariesIfMultipleApisDeployed() {
        givenWillReturnMultipleSuccess();

        List<ApiApplication> applications = action.listAllApplications();

        assertThat(applications.size()).isEqualTo(2);
        assertThat(applications.get(0)).isEqualToComparingFieldByField(new DefaultApplication());
        assertThat(applications.get(1)).isEqualToComparingFieldByField(new TestApplication());
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnEmptySuccess() {
        String body = responseLoader.load("list-application-empty-success.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnMultipleSuccess() {
        String body = responseLoader.load("list-application-multiple-success.json");
        client.cannedResponse(200, body);
    }

}
