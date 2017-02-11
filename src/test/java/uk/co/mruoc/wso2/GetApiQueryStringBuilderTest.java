package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetApiQueryStringBuilderTest {

    private static final String NAME = "api-name";
    private static final String VERSION = "v1";
    private static final String PROVIDER = "admin";

    private final GetApiQueryStringBuilder builder = new GetApiQueryStringBuilder();

    @Test
    public void shouldBuildQueryString() {
        String expectedQueryString = "?action=getAPI&name=" + NAME + "&version=" + VERSION + "&provider=" + PROVIDER;

        String result = builder
                .setName(NAME)
                .setVersion(VERSION)
                .setProvider(PROVIDER)
                .build();

        assertThat(result).isEqualTo(expectedQueryString);
    }

}
