package uk.co.mruoc.wso2.store;

import org.junit.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertiesKeyGeneratorParamsTest {

    private final Properties properties = new Properties();

    @Test
    public void shouldSetApiNameFromProperties() {
        String apiName = "api-name";
        properties.setProperty("api.name", apiName);

        KeyGeneratorParams params = new PropertiesKeyGeneratorParams(properties);

        assertThat(params.getApiName()).isEqualTo(apiName);
    }

    @Test
    public void shouldSetApiVersionFromProperties() {
        String apiVersion = "v1";
        properties.setProperty("api.version", apiVersion);

        KeyGeneratorParams params = new PropertiesKeyGeneratorParams(properties);

        assertThat(params.getApiVersion()).isEqualTo(apiVersion);
    }

    @Test
    public void shouldSetProviderFromProperties() {
        String provider = "admin";
        properties.setProperty("api.provider", provider);

        KeyGeneratorParams params = new PropertiesKeyGeneratorParams(properties);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

    @Test
    public void shouldSetApplicationNameFromProperties() {
        String applicationName = "application-name";
        properties.setProperty("api.application.name", applicationName);

        KeyGeneratorParams params = new PropertiesKeyGeneratorParams(properties);

        assertThat(params.getApplicationName()).isEqualTo(applicationName);
    }

}
