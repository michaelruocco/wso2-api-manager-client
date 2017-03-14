package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreLoginUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/store/site/blocks/user/login/ajax/login.jag";

    private static final String LOGIN_QUERY_STRING = "?action=login&username=%s&password=%s";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";

    private final LoginUrlBuilder builder = new StoreLoginUrlBuilder(HOST);

    @Test
    public void shouldBuildUrl() {
        String expectedUrl = HOST + RESOURCE + String.format(LOGIN_QUERY_STRING, USERNAME, PASSWORD);
        Credentials credentials = new Credentials(USERNAME, PASSWORD);

        String result = builder.build(credentials);

        assertThat(result).isEqualTo(expectedUrl);
    }

}
