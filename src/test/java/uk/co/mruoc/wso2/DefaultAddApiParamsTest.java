package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.wso2.DefaultAddApiParams.DefaultAddApiParamsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiSubscriptions.*;

public class DefaultAddApiParamsTest {

    private final DefaultAddApiParamsBuilder builder = new DefaultAddApiParamsBuilder();

    @Test
    public void shouldSetName() {
        String name = "api-name";

        AddApiParams params = builder.setName(name).build();

        assertThat(params.getName()).isEqualTo(name);
    }

    @Test
    public void shouldSetContext() {
        String context = "/api/context";

        AddApiParams params = builder.setContext(context).build();

        assertThat(params.getContext()).isEqualTo(context);
    }

    @Test
    public void shouldSetVersion() {
        String version = "/v1";

        AddApiParams params = builder.setVersion(version).build();

        assertThat(params.getVersion()).isEqualTo(version);
    }

    @Test
    public void shouldSetInSequence() {
        String sequence = "inSequence";

        AddApiParams params = builder.setInSequence(sequence).build();

        assertThat(params.getInSequence()).isEqualTo(sequence);
    }

    @Test
    public void shouldSetOutSequence() {
        String sequence = "outSequence";

        AddApiParams params = builder.setOutSequence(sequence).build();

        assertThat(params.getOutSequence()).isEqualTo(sequence);
    }

    @Test
    public void shouldSetIsDefaultVersion() {
        AddApiParams params = builder.setIsDefaultVersion(true).build();

        assertThat(params.isDefaultVersion()).isTrue();
    }

    @Test
    public void shouldSetResponseCacheEnabled() {
        AddApiParams params = builder.setResponseCacheEnabled(true).build();

        assertThat(params.isResponseCacheEnabled()).isTrue();
    }

    @Test
    public void shouldSetResponseCacheTimeout() {
        int timeout = 400;

        AddApiParams params = builder.setResponseCacheTimeout(timeout).build();

        assertThat(params.getResponseCacheTimeout()).isEqualTo(timeout);
    }

    @Test
    public void subscriptionsShouldDefaultToCurrentTenant() {
        AddApiParams params = builder.build();

        assertThat(params.getSubscriptions()).isEqualTo(CURRENT_TENANT);
    }

    @Test
    public void shouldSetSubscriptions() {
        ApiSubscriptions subscriptions = ALL_TENANTS;

        AddApiParams params = builder.setSubscriptions(subscriptions).build();

        assertThat(params.getSubscriptions()).isEqualTo(subscriptions);
    }

    @Test
    public void tenantsShouldDefaultToEmptyList() {
        AddApiParams params = builder.build();

        assertThat(params.getTenants()).isEmpty();
    }

    @Test
    public void shouldSetTenants() {
        AddApiParams params = builder.setTenants("tenant1", "tenant2").build();

        assertThat(params.getTenants()).containsExactly("tenant1", "tenant2");
    }

}
