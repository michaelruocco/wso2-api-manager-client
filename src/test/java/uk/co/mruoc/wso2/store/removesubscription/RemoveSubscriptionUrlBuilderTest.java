package uk.co.mruoc.wso2.store.removesubscription;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RemoveSubscriptionUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/store/site/blocks/subscription/subscription-remove/ajax/subscription-remove.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final RemoveSubscriptionParamsToQueryStringConverter queryStringConverter = mock(RemoveSubscriptionParamsToQueryStringConverter.class);
    private final RemoveSubscriptionUrlBuilder builder = new RemoveSubscriptionUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        RemoveSubscriptionParams params = mock(RemoveSubscriptionParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
