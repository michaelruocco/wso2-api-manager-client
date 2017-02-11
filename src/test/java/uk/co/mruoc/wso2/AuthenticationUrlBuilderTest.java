package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/publisher/site/blocks/user/login/ajax/login.jag";

    private static final String LOGIN_QUERY_STRING = "?action=login&username=%s&password=%s";
    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";

    private final AuthenticationUrlBuilder builder = new AuthenticationUrlBuilder(HOST);

    @Test
    public void shouldBuildLoginUrl() {
        String expectedUrl = HOST + RESOURCE + String.format(LOGIN_QUERY_STRING, USERNAME, PASSWORD);
        Credentials credentials = new Credentials(USERNAME, PASSWORD);

        String result = builder.buildLoginUrl(credentials);

        assertThat(result).isEqualTo(expectedUrl);
    }

    @Test
    public void shouldBuildLogoutUrl() {
        String expectedUrl = HOST + RESOURCE + LOGOUT_QUERY_STRING;

        String result = builder.buildLogoutUrl();

        assertThat(result).isEqualTo(expectedUrl);
    }

}
