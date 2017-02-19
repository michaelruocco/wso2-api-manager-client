package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultGetApiParamsTest {

    private final DefaultGetApiParams params = new DefaultGetApiParams();

    @Test
    public void nameShouldDefaultToEmptyString() {
        assertThat(params.getName()).isEmpty();
    }

    @Test
    public void versionShouldDefaultToEmptyString() {
        assertThat(params.getVersion()).isEmpty();
    }

    @Test
    public void providerShouldDefaultToEmptyString() {
        assertThat(params.getProvider()).isEmpty();
    }

    @Test
    public void shouldSetName() {
        String name = "api-name";

        params.setName(name);

        assertThat(params.getName()).isEqualTo(name);
    }

    @Test
    public void shouldSetVersion() {
        String version = "v1";

        params.setVersion(version);

        assertThat(params.getVersion()).isEqualTo(version);
    }

    @Test
    public void shouldSetProvider() {
        String provider = "admin";

        params.setProvider(provider);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

}
