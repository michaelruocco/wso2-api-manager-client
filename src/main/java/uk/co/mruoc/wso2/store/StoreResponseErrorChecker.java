package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

public class StoreResponseErrorChecker extends ResponseErrorChecker {

    @Override
    public void checkForError(Response response) {
        if (hasError(response))
            throw new ApiStoreException(buildErrorMessage(response));
    }

}
