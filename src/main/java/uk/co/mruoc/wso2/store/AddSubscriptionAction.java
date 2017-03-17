package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AddSubscriptionAction {

    private ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private HttpClient client;
    private AddSubscriptionUrlBuilder urlBuilder;

    public AddSubscriptionAction(HttpClient client, AddSubscriptionUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean addSubscription(AddSubscriptionParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
