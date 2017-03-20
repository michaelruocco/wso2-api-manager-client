package uk.co.mruoc.wso2.publisher.addapi;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;
import uk.co.mruoc.wso2.publisher.PublisherResponseErrorChecker;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AddApiAction {

    private final ResponseErrorChecker errorChecker = new PublisherResponseErrorChecker();
    private HttpClient client;
    private AddApiUrlBuilder urlBuilder;

    public AddApiAction(HttpClient client, String hostUrl) {
        this(client, new AddApiUrlBuilder(hostUrl));
    }

    public AddApiAction(HttpClient client, AddApiUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean addApi(AddApiParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
