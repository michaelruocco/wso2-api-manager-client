package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AddApiAction {

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
        ResponseErrorChecker.checkForError(response);
        return true;
    }

}
