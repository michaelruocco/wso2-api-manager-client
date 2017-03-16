package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

public class PublisherResponseErrorChecker extends ResponseErrorChecker {

    @Override
    public void checkForError(Response response) {
        if (hasError(response))
            throw new ApiPublisherException(buildErrorMessage(response));
    }

}
