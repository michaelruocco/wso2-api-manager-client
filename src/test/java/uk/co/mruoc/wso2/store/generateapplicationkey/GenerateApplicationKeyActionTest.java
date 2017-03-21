package uk.co.mruoc.wso2.store.generateapplicationkey;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.http.client.FakeHttpClient;
import uk.co.mruoc.wso2.ApiManagerException;
import uk.co.mruoc.wso2.ResponseLoader;
import uk.co.mruoc.wso2.store.StoreResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GenerateApplicationKeyActionTest {

    private static final String URL = "generate-application-key-url";

    private final ResponseLoader responseLoader = new StoreResponseLoader();
    private final FakeHttpClient client = new FakeHttpClient();
    private final GenerateApplicationKeyUrlBuilder urlBuilder = mock(GenerateApplicationKeyUrlBuilder.class);
    private final GenerateApplicationKeyParams params = mock(GenerateApplicationKeyParams.class);

    private final GenerateApplicationKeyAction action = new GenerateApplicationKeyAction(client, urlBuilder);

    @Before
    public void setUp() {
        given(urlBuilder.build(params)).willReturn(URL);
    }

    @Test
    public void shouldCallCorrectUrl() {
        givenWillReturnSuccess();

        action.generateKey(params);

        assertThat(client.lastRequestUri()).isEqualTo(URL);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionIfNon200Response() {
        givenWillReturnNon200();

        action.generateKey(params);
    }

    @Test(expected = ApiManagerException.class)
    public void shouldThrowExceptionOnGenerateKeyFailure() {
        givenWillReturnFailure();

        action.generateKey(params);
    }

    @Test
    public void shouldReturnKeyOnGenerateKeySuccess() {
        givenWillReturnSuccess();

        ApplicationKey key = action.generateKey(params);

        assertThat(key).isEqualToComparingFieldByField(new FakeApplicationKey());
    }

    private void givenWillReturnNon200() {
        client.cannedResponse(500, "");
    }

    private void givenWillReturnFailure() {
        String body = responseLoader.load("generate-application-key-failure.json");
        client.cannedResponse(200, body);
    }

    private void givenWillReturnSuccess() {
        String body = responseLoader.load("generate-application-key-success.json");
        client.cannedResponse(200, body);
    }

}
