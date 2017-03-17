package uk.co.mruoc.wso2.publisher;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.SelectApiParams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RemoveApiActionTest {

    private static final String URL = "remove-api-url";

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final RemoveApiUrlBuilder urlBuilder = mock(RemoveApiUrlBuilder.class);
    private final SelectApiParams params = mock(SelectApiParams.class);

    private final RemoveApiAction action = new RemoveApiAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.removeApi(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.removeApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnFailure() {
        givenWillReturnFailure();

        action.removeApi(params);
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillReturnSuccess();

        assertThat(action.removeApi(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("remove-api-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("remove-api-success.json");
        client.cannedResponse(200, body);
    }

}
