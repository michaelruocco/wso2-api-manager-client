package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

public class ApiExistsAction {

    private final ResponseErrorChecker errorChecker = new PublisherResponseErrorChecker();
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
        errorChecker.checkForError(response);
        return exists(response);
    }

    private boolean exists(Response response) {
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getExists();
    }

}
