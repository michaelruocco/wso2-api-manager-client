package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CredentialsTest {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";

    private Credentials credentials = new Credentials(USERNAME, PASSWORD);

    @Test
    public void shouldReturnUsername() {
        assertThat(credentials.getUsername()).isEqualTo(USERNAME);
    }

    @Test
    public void shouldReturnPassword() {
        assertThat(credentials.getPassword()).isEqualTo(PASSWORD);
    }

}
