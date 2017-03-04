package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultRemoveApiUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/publisher/site/blocks/item-add/ajax/remove.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final RemoveApiParamsToQueryStringConverter queryStringConverter = mock(RemoveApiParamsToQueryStringConverter.class);
    private final RemoveApiUrlBuilder builder = new DefaultRemoveApiUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        SelectApiParams params = mock(SelectApiParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
