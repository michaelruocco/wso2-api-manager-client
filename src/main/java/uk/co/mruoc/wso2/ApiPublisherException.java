package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.Response;

public class ApiPublisherException extends RuntimeException {

    public ApiPublisherException(Response response) {
        super(buildMessage(response));
    }

    public ApiPublisherException(String message) {
        super(message);
    }

    private static String buildMessage(Response response) {
        return "status code: " + response.getStatusCode() + " body: " + response.getBody();
    }

}
