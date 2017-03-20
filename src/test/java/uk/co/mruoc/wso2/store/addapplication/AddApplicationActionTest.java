package uk.co.mruoc.wso2.store.addapplication;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ApiManagerException;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AddApplicationActionTest {

    private static final String URL = "add-application-url";

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final AddApplicationUrlBuilder urlBuilder = mock(AddApplicationUrlBuilder.class);
    private final AddApplicationParams params = mock(AddApplicationParams.class);

    private final AddApplicationAction action = new AddApplicationAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.addApplication(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.addApplication(params);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionOnAddApiFailure() {
        givenWillReturnFailure();

        action.addApplication(params);
    }

    @Test
    public void shouldReturnTrueOnAddApiSuccess() {
        givenWillReturnSuccess();

        assertThat(action.addApplication(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("add-application-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("add-application-success.json");
        client.cannedResponse(200, body);
    }

}
