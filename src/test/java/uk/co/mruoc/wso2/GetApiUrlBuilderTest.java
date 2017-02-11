package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.wso2.DefaultGetApiParams.DefaultGetApiParamsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class GetApiUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/publisher/site/blocks/listing/ajax/item-list.jag";
    private static final String QUERY_STRING = "?action=getAPI&name=%s&version=%s&provider=%s";

    private static final String NAME = "api-name";
    private static final String VERSION = "v1";
    private static final String PROVIDER = "admin";

    private final GetApiUrlBuilder builder = new GetApiUrlBuilder(HOST);

    @Test
    public void shouldBuildUrl() {
        String expectedUrl = HOST + RESOURCE + String.format(QUERY_STRING, NAME, VERSION, PROVIDER);
        GetApiParams params = new DefaultGetApiParamsBuilder()
                .setName(NAME)
                .setVersion(VERSION)
                .setProvider(PROVIDER)
                .build();

        String result = builder.build(params);

        assertThat(result).isEqualTo(expectedUrl);
    }

}
