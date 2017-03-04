package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class SetStatusAction {

    private HttpClient client;
    private SetStatusUrlBuilder urlBuilder;

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
        ResponseErrorChecker.checkForError(response);
        return true;
    }

}