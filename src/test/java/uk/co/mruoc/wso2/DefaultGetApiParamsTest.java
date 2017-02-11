package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.wso2.DefaultGetApiParams.DefaultGetApiParamsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultGetApiParamsTest {

    private final DefaultGetApiParamsBuilder builder = new DefaultGetApiParamsBuilder();

    @Test
    public void shouldSetName() {
        String name = "api-name";

        GetApiParams params = builder.setName(name).build();

        assertThat(params.getName()).isEqualTo(name);
    }

    @Test
    public void shouldSetVersion() {
        String version = "v1";

        GetApiParams params = builder.setVersion(version).build();

        assertThat(params.getVersion()).isEqualTo(version);
    }

    @Test
    public void shouldSetProvider() {
        String provider = "admin";

        GetApiParams params = builder.setProvider(provider).build();

        assertThat(params.getProvider()).isEqualTo(provider);
    }

}
