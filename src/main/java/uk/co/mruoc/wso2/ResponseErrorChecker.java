package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.Response;

public class ResponseErrorChecker {

    public static void checkForError(Response response) {
        if (hasError(response))
            throw new ApiPublisherException(buildErrorMessage(response));
    }

    private static boolean hasError(Response response) {
        if (response.getStatusCode() != 200)
            return true;
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getError();
    }

    private static String buildErrorMessage(Response response) {
        return "status code: " + response.getStatusCode() + " body: " + response.getBody();
    }

}
