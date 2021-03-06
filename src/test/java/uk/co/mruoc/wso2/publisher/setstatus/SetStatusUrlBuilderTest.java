package uk.co.mruoc.wso2.publisher.setstatus;

import org.junit.Test;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParams;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParamsToQueryStringConverter;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusUrlBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SetStatusUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/publisher/site/blocks/life-cycles/ajax/life-cycles.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final SetStatusParamsToQueryStringConverter queryStringConverter = mock(SetStatusParamsToQueryStringConverter.class);
    private final SetStatusUrlBuilder builder = new SetStatusUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        SetStatusParams params = mock(SetStatusParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
