package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

public class LogoutAction {

    private final HttpClient client;
    private final LogoutUrlBuilder  urlBuilder;

    public LogoutAction(HttpClient client, String hostUrl) {
        this(client, new DefaultLogoutUrlBuilder(hostUrl));
    }

    public LogoutAction(HttpClient client, LogoutUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean logout() {
        String url = urlBuilder.build();
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

}
