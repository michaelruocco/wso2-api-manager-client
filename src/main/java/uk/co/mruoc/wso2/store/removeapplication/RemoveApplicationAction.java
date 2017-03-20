package uk.co.mruoc.wso2.store.removeapplication;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;
import uk.co.mruoc.wso2.store.StoreResponseErrorChecker;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RemoveApplicationAction {

    private final ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private final HttpClient client;
    private final RemoveApplicationUrlBuilder urlBuilder;

    public RemoveApplicationAction(HttpClient client, String hostUrl) {
        this(client, new RemoveApplicationUrlBuilder(hostUrl));
    }

    public RemoveApplicationAction(HttpClient client, RemoveApplicationUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean removeApplication(String name) {
        String url = urlBuilder.build(name);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
