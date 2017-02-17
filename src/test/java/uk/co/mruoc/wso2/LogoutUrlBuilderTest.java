package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/publisher/site/blocks/user/login/ajax/login.jag";

    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private final LogoutUrlBuilder builder = new DefaultLogoutUrlBuilder(HOST);

    @Test
    public void shouldBuildLogoutUrl() {
        String expectedUrl = HOST + RESOURCE + LOGOUT_QUERY_STRING;

        String result = builder.build();

        assertThat(result).isEqualTo(expectedUrl);
    }

}
