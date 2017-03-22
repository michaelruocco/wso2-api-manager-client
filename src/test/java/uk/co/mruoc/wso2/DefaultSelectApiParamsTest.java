package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultSelectApiParamsTest {

    private final DefaultSelectApiParams params = new DefaultSelectApiParams();

    @Test
    public void apiNameShouldDefaultToEmptyString() {
        assertThat(params.getApiName()).isEmpty();
    }

    @Test
    public void apiVersionShouldDefaultToEmptyString() {
        assertThat(params.getApiVersion()).isEmpty();
    }

    @Test
    public void providerShouldDefaultToEmptyString() {
        assertThat(params.getProvider()).isEmpty();
    }

    @Test
    public void shouldSetApiName() {
        String name = "api-name";

        params.setApiName(name);

        assertThat(params.getApiName()).isEqualTo(name);
    }

    @Test
    public void shouldSetApiVersion() {
        String version = "v1";

        params.setApiVersion(version);

        assertThat(params.getApiVersion()).isEqualTo(version);
    }

    @Test
    public void shouldSetProvider() {
        String provider = "admin";

        params.setProvider(provider);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

}
