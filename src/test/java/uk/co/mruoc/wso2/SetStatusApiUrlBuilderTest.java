package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SetStatusApiUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/publisher/site/blocks/life-cycles/ajax/life-cycles.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final SetStatusApiParamsToQueryStringConverter queryStringConverter = mock(SetStatusApiParamsToQueryStringConverter.class);
    private final SetStatusApiUrlBuilder builder = new DefaultSetStatusApiUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        SetStatusApiParams params = mock(SetStatusApiParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
