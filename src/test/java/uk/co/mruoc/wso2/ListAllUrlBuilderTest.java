package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListAllUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/publisher/site/blocks/listing/ajax/item-list.jag";
    private static final String QUERY_STRING = "?action=getAllAPIs";

    private final ListAllUrlBuilder builder = new ListAllUrlBuilder(HOST);

    @Test
    public void shouldBuildUrl() {
        String expectedUrl = HOST + RESOURCE + QUERY_STRING;

        String result = builder.build();

        assertThat(result).isEqualTo(expectedUrl);
    }

}
