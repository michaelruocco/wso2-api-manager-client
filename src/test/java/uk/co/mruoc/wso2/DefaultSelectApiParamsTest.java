package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultSelectApiParamsTest {

    private final DefaultSelectApiParams params = new DefaultSelectApiParams();

    @Test
    public void nameShouldDefaultToEmptyString() {
        assertThat(params.getApiName()).isEmpty();
    }

    @Test
    public void versionShouldDefaultToEmptyString() {
        assertThat(params.getApiVersion()).isEmpty();
    }

    @Test
    public void providerShouldDefaultToEmptyString() {
        assertThat(params.getProvider()).isEmpty();
    }

    @Test
    public void shouldSetName() {
        String name = "api-name";

        params.setName(name);

        assertThat(params.getApiName()).isEqualTo(name);
    }

    @Test
    public void shouldSetVersion() {
        String version = "v1";

        params.setVersion(version);

        assertThat(params.getApiVersion()).isEqualTo(version);
    }

    @Test
    public void shouldSetProvider() {
        String provider = "admin";

        params.setProvider(provider);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

}
