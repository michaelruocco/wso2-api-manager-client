package uk.co.mruoc.wso2.publisher.apiexists;

import org.junit.Test;
import uk.co.mruoc.wso2.publisher.apiexists.ApiExistsUrlBuilder;
import uk.co.mruoc.wso2.publisher.apiexists.NameToApiExistsQueryStringConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ApiExistsUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/publisher/site/blocks/item-add/ajax/add.jag";
    private static final String QUERY_STRING = "?queryString=value";
    private static final String NAME = "name";

    private final NameToApiExistsQueryStringConverter queryStringConverter = mock(NameToApiExistsQueryStringConverter.class);
    private final ApiExistsUrlBuilder builder = new ApiExistsUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        given(queryStringConverter.convert(NAME)).willReturn(QUERY_STRING);

        String url = builder.build(NAME);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
