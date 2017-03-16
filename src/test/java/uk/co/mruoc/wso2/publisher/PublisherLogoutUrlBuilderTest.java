package uk.co.mruoc.wso2.publisher;

import org.junit.Test;
import uk.co.mruoc.wso2.LogoutUrlBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherLogoutUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/publisher/site/blocks/user/login/ajax/login.jag";

    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private final LogoutUrlBuilder builder = new PublisherLogoutUrlBuilder(HOST);

    @Test
    public void shouldBuildLogoutUrl() {
        String expectedUrl = HOST + RESOURCE + LOGOUT_QUERY_STRING;

        String result = builder.build();

        assertThat(result).isEqualTo(expectedUrl);
    }

}
