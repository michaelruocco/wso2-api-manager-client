package uk.co.mruoc.wso2.store;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AddSubscriptionUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/store/site/blocks/subscription/subscription-add/ajax/subscription-add.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final AddSubscriptionParamsToQueryStringConverter queryStringConverter = mock(AddSubscriptionParamsToQueryStringConverter.class);
    private final AddSubscriptionUrlBuilder builder = new AddSubscriptionUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        AddSubscriptionParams params = mock(AddSubscriptionParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
