package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class SetStatusAction {

    private final ResponseErrorChecker errorChecker = new PublisherResponseErrorChecker();
    private final HttpClient client;
    private final SetStatusUrlBuilder urlBuilder;

    public SetStatusAction(HttpClient client, String hostUrl) {
        this(client, new SetStatusUrlBuilder(hostUrl));
    }

    public SetStatusAction(HttpClient client, SetStatusUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean setStatus(SetStatusParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
