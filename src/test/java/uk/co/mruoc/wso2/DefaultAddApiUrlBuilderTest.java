package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DefaultAddApiUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/publisher/site/blocks/item-add/ajax/add.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final AddApiParamsToQueryStringConverter queryStringConverter = new StubAddApiParamsToQueryStringConverter(QUERY_STRING);
    private final AddApiUrlBuilder builder = new DefaultAddApiUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        AddApiParams params = mock(AddApiParams.class);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
