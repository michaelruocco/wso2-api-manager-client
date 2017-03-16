package uk.co.mruoc.wso2.store;

import org.junit.Test;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class StoreResponseErrorCheckerTest {

    private final Response response = mock(Response.class);

    private final ResponseErrorChecker checker = new StoreResponseErrorChecker();

    @Test(expected = ApiStoreException.class)
    public void shouldThrowExceptionIfError() {
        given(response.getStatusCode()).willReturn(500);

        checker.checkForError(response);
    }

}
