package uk.co.mruoc.wso2.store.removesubscription;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ApiManagerException;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RemoveSubscriptionActionTest {

    private static final String URL = "remove-subscription-url";

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final RemoveSubscriptionUrlBuilder urlBuilder = mock(RemoveSubscriptionUrlBuilder.class);
    private final RemoveSubscriptionParams params = mock(RemoveSubscriptionParams.class);

    private final RemoveSubscriptionAction action = new RemoveSubscriptionAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.removeSubscription(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.removeSubscription(params);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionOnAddApiFailure() {
        givenWillReturnFailure();

        action.removeSubscription(params);
    }

    @Test
    public void shouldReturnTrueOnAddApiSuccess() {
        givenWillReturnSuccess();

        assertThat(action.removeSubscription(params)).isTrue();
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("remove-subscription-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("remove-subscription-success.json");
        client.cannedResponse(200, body);
    }

}
