package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiExistsActionTest {

    private static final String URL = "api-exists-url";

    private final ResponseLoader responseLoader = new ResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final ApiExistsUrlBuilder urlBuilder = new StubApiExistsUrlBuilder(URL);
    private final ApiExistsAction action = new ApiExistsAction(client, urlBuilder);

    @Test
    public void apiExistsShouldCallCorrectUrl() {
        givenWillReturnApiExistsSuccess();

        action.apiExists("name");

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiPublisherException.class)
    public void apiExistsShouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.apiExists("name");
    }

    @Test
    public void apiExistsShouldReturnFalseFailure() {
        givenWillReturnApiExistsFailure();

        assertThat(action.apiExists("name")).isFalse();
    }

    @Test
    public void apiExistsShouldReturnTrueOnSuccess() {
        givenWillReturnApiExistsSuccess();

        assertThat(action.apiExists("name")).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnApiExistsFailure() {
        String body = responseLoader.load("api-exists-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnApiExistsSuccess() {
        String body = responseLoader.load("api-exists-success.json");
        client.cannedResponse(200, body);
    }

}
