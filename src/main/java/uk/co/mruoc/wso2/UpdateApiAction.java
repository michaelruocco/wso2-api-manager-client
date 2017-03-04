package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class UpdateApiAction {

    private final HttpClient client;
    private final UpdateApiUrlBuilder urlBuilder;

    public UpdateApiAction(HttpClient client, String hostUrl) {
        this(client, new UpdateApiUrlBuilder(hostUrl));
    }

    public UpdateApiAction(HttpClient client, UpdateApiUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean updateApi(UpdateApiParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

}
