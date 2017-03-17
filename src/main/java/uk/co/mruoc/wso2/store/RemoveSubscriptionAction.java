package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RemoveSubscriptionAction {

    private ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private HttpClient client;
    private RemoveSubscriptionUrlBuilder urlBuilder;

    public RemoveSubscriptionAction(HttpClient client, String hostUrl) {
        this(client, new RemoveSubscriptionUrlBuilder(hostUrl));
    }

    public RemoveSubscriptionAction(HttpClient client, RemoveSubscriptionUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean removeSubscription(RemoveSubscriptionParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
