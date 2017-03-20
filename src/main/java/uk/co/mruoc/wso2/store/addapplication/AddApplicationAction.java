package uk.co.mruoc.wso2.store.addapplication;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;
import uk.co.mruoc.wso2.store.StoreResponseErrorChecker;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AddApplicationAction {

    private ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private HttpClient client;
    private AddApplicationUrlBuilder urlBuilder;

    public AddApplicationAction(HttpClient client, String hostUrl) {
        this(client, new AddApplicationUrlBuilder(hostUrl));
    }

    public AddApplicationAction(HttpClient client, AddApplicationUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean addApplication(AddApplicationParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
