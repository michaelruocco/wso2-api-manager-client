package uk.co.mruoc.wso2.publisher.apiexists;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.publisher.ApiPublisherException;
import uk.co.mruoc.wso2.publisher.PublisherResponseLoader;
import uk.co.mruoc.wso2.publisher.apiexists.ApiExistsAction;
import uk.co.mruoc.wso2.publisher.apiexists.ApiExistsUrlBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ApiExistsActionTest {

    private static final String URL = "api-exists-url";
    private static final String NAME = "name";

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final ApiExistsUrlBuilder urlBuilder = mock(ApiExistsUrlBuilder.class);

    private final ApiExistsAction action = new ApiExistsAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(NAME)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.apiExists(NAME);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.apiExists(NAME);
    }

    @Test
    public void shouldReturnFalseOnFailure() {
        givenWillReturnFailure();

        assertThat(action.apiExists(NAME)).isFalse();
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillReturnSuccess();

        assertThat(action.apiExists("name")).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("api-exists-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("api-exists-success.json");
        client.cannedResponse(200, body);
    }

}
