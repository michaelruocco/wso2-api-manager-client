package uk.co.mruoc.wso2.publisher.getapi;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.ApiPublisherException;
import uk.co.mruoc.wso2.publisher.PublisherResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetApiActionTest {

    private static final String URL = "get-api-url";

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final GetApiUrlBuilder urlBuilder = mock(GetApiUrlBuilder.class);
    private final SelectApiParams params = mock(SelectApiParams.class);

    private final GetApiAction action = new GetApiAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.getApi(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.getApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfApiNotFound() {
        givenWillReturnFailure();

        action.getApi(params);
    }

    @Test
    public void shouldReturnApiIfExists() {
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
