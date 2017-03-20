package uk.co.mruoc.wso2.store.getsubscription;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ApiManagerException;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetSubscriptionsActionTest {

    private static final String URL = "get-subscription-url";

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final GetSubscriptionUrlBuilder urlBuilder = mock(GetSubscriptionUrlBuilder.class);
    private final SelectApiParams params = mock(SelectApiParams.class);

    private final GetSubscriptionsAction action = new GetSubscriptionsAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.getSubscriptions(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.getSubscriptions(params);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionOnGetSubscriptionFailure() {
        givenWillReturnFailure();

        action.getSubscriptions(params);
    }

    @Test
    public void shouldReturnSubscriptionOnGetSubscriptionSuccess() {
        givenWillReturnSuccess();

        List<ApiSubscription> subscriptions = action.getSubscriptions(params);

        assertThat(subscriptions.size()).isEqualTo(1);
        assertThat(subscriptions.get(0)).isEqualToComparingFieldByField(new TestApiSubscription());
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("get-subscription-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("get-subscription-success.json");
        client.cannedResponse(200, body);
    }

}
