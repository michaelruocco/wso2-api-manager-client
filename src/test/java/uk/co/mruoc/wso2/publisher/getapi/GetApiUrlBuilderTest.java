package uk.co.mruoc.wso2.publisher.getapi;

import org.junit.Test;
import uk.co.mruoc.wso2.SelectApiParams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetApiUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/publisher/site/blocks/listing/ajax/item-list.jag";
    private static final String QUERY_STRING = "?action=getAPI&name=%s&version=%s&provider=%s";

    private final GetApiParamsToQueryStringConverter queryStringConverter = mock(GetApiParamsToQueryStringConverter.class);
    private final GetApiUrlBuilder builder = new GetApiUrlBuilder(HOST, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        SelectApiParams params = mock(SelectApiParams.class);
        given(queryStringConverter.convert(params)).willReturn(QUERY_STRING);

        String result = builder.build(params);

        assertThat(result).isEqualTo( HOST + RESOURCE + QUERY_STRING);
    }

}
