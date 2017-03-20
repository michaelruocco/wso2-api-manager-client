package uk.co.mruoc.wso2.store.removeapplication;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.store.ApiStoreException;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RemoveApplicationActionTest {

    private static final String URL = "remove-application-url";
    private static final String NAME = "application-name";

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final RemoveApplicationUrlBuilder urlBuilder = mock(RemoveApplicationUrlBuilder.class);

    private final RemoveApplicationAction action = new RemoveApplicationAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(NAME)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.removeApplication(NAME);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiStoreException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.removeApplication(NAME);
    }

    @Test(expected = ApiStoreException.class)
    public void shouldThrowExceptionOnFailure() {
        givenWillReturnFailure();

        action.removeApplication(NAME);
    }

    @Test
    public void shouldReturnTrueOnSuccess() {
        givenWillReturnSuccess();

        assertThat(action.removeApplication(NAME)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("remove-application-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("remove-application-success.json");
        client.cannedResponse(200, body);
    }

}
