package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.Response;

public abstract class ResponseErrorChecker {

    public abstract void checkForError(Response response);

    protected boolean hasError(Response response) {
        if (response.getStatusCode() != 200)
            return true;
        ErrorJsonParser parser = new ErrorJsonParser(response.getBody());
        return parser.getError();
    }

    protected String buildErrorMessage(Response response) {
        return "status code: " + response.getStatusCode() + " body: " + response.getBody();
    }

}
