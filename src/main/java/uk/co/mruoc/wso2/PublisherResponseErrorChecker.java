package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.Response;

public class PublisherResponseErrorChecker extends ResponseErrorChecker {

    @Override
    public void checkForError(Response response) {
        if (hasError(response))
            throw new ApiPublisherException(buildErrorMessage(response));
    }

}
