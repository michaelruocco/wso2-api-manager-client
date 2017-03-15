package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class LoginAction {

    private final HttpClient client;
    private final LoginUrlBuilder urlBuilder;
    private final ResponseErrorChecker errorChecker;

    public LoginAction(HttpClient client, LoginUrlBuilder urlBuilder, ResponseErrorChecker errorChecker) {
        this.client = client;
        this.urlBuilder = urlBuilder;
        this.errorChecker = errorChecker;
    }

    public boolean login(Credentials credentials) {
        String url = urlBuilder.build(credentials);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return true;
    }

}
