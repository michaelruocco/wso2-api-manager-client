package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.Response;

public class StoreResponseErrorChecker extends ResponseErrorChecker {

    @Override
    public void checkForError(Response response) {
        if (hasError(response))
            throw new ApiStoreException(buildErrorMessage(response));
    }

}
