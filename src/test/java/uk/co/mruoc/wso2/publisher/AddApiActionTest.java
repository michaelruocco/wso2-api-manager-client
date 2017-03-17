package uk.co.mruoc.wso2.publisher;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AddApiActionTest {

    private static final String URL = "add-api-url";

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final AddApiUrlBuilder urlBuilder = mock(AddApiUrlBuilder.class);
    private final AddApiParams params = mock(AddApiParams.class);

    private final AddApiAction action = new AddApiAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.addApi(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.addApi(params);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionOnAddApiFailure() {
        givenWillReturnFailure();

        action.addApi(params);
    }

    @Test
    public void shouldReturnTrueOnAddApiSuccess() {
        givenWillReturnSuccess();

        assertThat(action.addApi(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("add-api-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("add-api-success.json");
        client.cannedResponse(200, body);
    }

}
