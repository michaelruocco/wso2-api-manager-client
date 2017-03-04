package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

public class ApiExistsAction {

    private final HttpClient client;
    private final ApiExistsUrlBuilder urlBuilder;

    public ApiExistsAction(HttpClient client, String hostUrl) {
        this(client, new ApiExistsUrlBuilder(hostUrl));
    }

    public ApiExistsAction(HttpClient client, ApiExistsUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean apiExists(String name) {
        String url = urlBuilder.build(name);
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return exists(response);
    }

    private boolean exists(Response response) {
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getExists();
    }

}
