package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

public class LogoutAction {

    private final HttpClient client;
    private final LogoutUrlBuilder  urlBuilder;
    private final ResponseErrorChecker errorChecker;

    public LogoutAction(HttpClient client, LogoutUrlBuilder urlBuilder, ResponseErrorChecker errorChecker) {
        this.client = client;
        this.urlBuilder = urlBuilder;
        this.errorChecker = errorChecker;
    }

    public boolean logout() {
        String url = urlBuilder.build();
        Response response = client.get(url);
        errorChecker.checkForError(response);
        return true;
    }

}
