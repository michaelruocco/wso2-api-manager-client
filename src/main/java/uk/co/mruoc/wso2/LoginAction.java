package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class LoginAction {

    private final HttpClient client;
    private final LoginUrlBuilder urlBuilder;

    public LoginAction(HttpClient client, LoginUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public boolean login(Credentials credentials) {
        String url = urlBuilder.build(credentials);
        Response response = client.post(url, EMPTY);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

}
