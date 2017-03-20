package uk.co.mruoc.wso2.store.getsubscription;

import org.junit.Test;
import uk.co.mruoc.wso2.SelectApiParams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetSubscriptionUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/store/site/blocks/subscription/subscription-list/ajax/subscription-list.jag";
    private static final String QUERY_STRING = "?action=getSubscriptionByAPI&apiName=%s&version=%s&provider=%s";

    private final SelectApiParamsToGetSubscriptionQueryStringConverter queryStringConverter = mock(SelectApiParamsToGetSubscriptionQueryStringConverter.class);
    private final GetSubscriptionUrlBuilder builder = new GetSubscriptionUrlBuilder(HOST, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        SelectApiParams params = mock(SelectApiParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String result = builder.build(params);

        assertThat(result).isEqualTo(HOST + RESOURCE + QUERY_STRING);
    }

}
