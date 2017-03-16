package uk.co.mruoc.wso2.store;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AddApplicationUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/store/site/blocks/application/application-add/ajax/application-add.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final AddApplicationParamsToQueryStringConverter queryStringConverter = mock(AddApplicationParamsToQueryStringConverter.class);
    private final AddApplicationUrlBuilder builder = new AddApplicationUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        AddApplicationParams params = mock(AddApplicationParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String url = builder.build(params);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
