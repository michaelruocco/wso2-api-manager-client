package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultApiManagerPropertiesTest {

    private final Properties properties = new Properties();

    @Test
    public void shouldSetApimUrl() {
        String apimUrl = givenApimUrlIsSetInProperties();

        ApiManagerProperties apiManagerProperties = new DefaultApiManagerProperties(properties);

        assertThat(apiManagerProperties.getApimUrl()).isEqualTo(apimUrl);
    }

    @Test
    public void shouldSetApimPublisherUrl() {
        String apimPublisherUrl = givenApimPublisherUrlIsSetInProperties();

        ApiManagerProperties apiManagerProperties = new DefaultApiManagerProperties(properties);

        assertThat(apiManagerProperties.getApimPublisherUrl()).isEqualTo(apimPublisherUrl);
    }

    @Test
    public void shouldSetReturnApimUrlIfPublisherUrlNotSet() {
        String apimUrl = givenApimUrlIsSetInProperties();

        ApiManagerProperties apiManagerProperties = new DefaultApiManagerProperties(properties);

        assertThat(apiManagerProperties.getApimPublisherUrl()).isEqualTo(apimUrl);
    }

    @Test
    public void shouldSetApimUsername() {
        String apimUsername = givenApimUsernameIsSetInProperties();

        ApiManagerProperties apiManagerProperties = new DefaultApiManagerProperties(properties);

        assertThat(apiManagerProperties.getApimUsername()).isEqualTo(apimUsername);
    }

    @Test
    public void shouldSetApimPassword() {
        String apimPassword = givenApimPasswordIsSetInProperties();

        ApiManagerProperties apiManagerProperties = new DefaultApiManagerProperties(properties);

        assertThat(apiManagerProperties.getApimPassword()).isEqualTo(apimPassword);
    }

    @Test
    public void shouldReturnApimCredentials() {
        String apimUsername = givenApimUsernameIsSetInProperties();
        String apimPassword = givenApimPasswordIsSetInProperties();

        ApiManagerProperties apiManagerProperties = new DefaultApiManagerProperties(properties);
        Credentials credentials = apiManagerProperties.getApimCredentials();

        assertThat(credentials.getUsername()).isEqualTo(apimUsername);
        assertThat(credentials.getPassword()).isEqualTo(apimPassword);
    }

    private String givenApimUrlIsSetInProperties() {
        String apimUrl = "http://localhost:8426";
        properties.setProperty("apim.url", apimUrl);
        return apimUrl;
    }

    private String givenApimPublisherUrlIsSetInProperties() {
        String apimPublisherUrl = "http://localhost:8426";
        properties.setProperty("apim.publisher.url", apimPublisherUrl);
        return apimPublisherUrl;
    }

    private String givenApimUsernameIsSetInProperties() {
        String apimUsername = "admin";
        properties.setProperty("apim.username", apimUsername);
        return apimUsername;
    }

    private String givenApimPasswordIsSetInProperties() {
        String apimPassword = "pa55word";
        properties.setProperty("apim.password", apimPassword);
        return apimPassword;
    }

}
