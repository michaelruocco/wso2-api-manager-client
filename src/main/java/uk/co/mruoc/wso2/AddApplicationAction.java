package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AddApplicationAction {

    private ResponseErrorChecker errorChecker = new PublisherResponseErrorChecker();
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
