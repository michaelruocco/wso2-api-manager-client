package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultApiExistsUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/publisher/site/blocks/item-add/ajax/add.jag";
    private static final String QUERY_STRING = "?queryString=value";

    private final NameToExistsQueryStringConverter queryStringConverter = new StubNameToExistsQueryStringConverter(QUERY_STRING);
    private final ApiExistsUrlBuilder builder = new DefaultApiExistsUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        String url = builder.build("name");

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
