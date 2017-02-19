package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiSubscriptions.*;

public class DefaultAddApiParamsTest {

    private final DefaultAddApiParams params = new DefaultAddApiParams();

    @Test(expected = UnsupportedOperationException.class)
    public void setProviderShouldNotBeSupported() {
        params.setProvider("provider");
    }

    @Test
    public void contextShouldDefaultToEmpty() {
        assertThat(params.getContext()).isEmpty();
    }

    @Test
    public void shouldSetContext() {
        String context = "/api/context";

        params.setContext(context);

        assertThat(params.getContext()).isEqualTo(context);
    }

    @Test
    public void inSequenceShouldDefaultToEmpty() {
        assertThat(params.getInSequence()).isEmpty();
    }

    @Test
    public void shouldSetInSequence() {
        String sequence = "inSequence";

        params.setInSequence(sequence);

        assertThat(params.getInSequence()).isEqualTo(sequence);
    }

    @Test
    public void outSequenceShouldDefaultToEmpty() {
        assertThat(params.getOutSequence()).isEmpty();
    }

    @Test
    public void shouldSetOutSequence() {
        String sequence = "outSequence";

        params.setOutSequence(sequence);

        assertThat(params.getOutSequence()).isEqualTo(sequence);
    }

    @Test
    public void defaultVersionShouldDefaultToFalse() {
        assertThat(params.isDefaultVersion()).isFalse();
    }

    @Test
    public void shouldSetIsDefaultVersion() {
        params.setDefaultVersion(true);

        assertThat(params.isDefaultVersion()).isTrue();
    }

    @Test
    public void responseCacheEnabledShouldDefaultToFalse() {
        assertThat(params.isResponseCacheEnabled()).isFalse();
    }

    @Test
    public void shouldSetResponseCacheEnabled() {
        params.setResponseCacheEnabled(true);

        assertThat(params.isResponseCacheEnabled()).isTrue();
    }

    @Test
    public void responseCacheTimeoutShouldDefaultTo300() {
        assertThat(params.getResponseCacheTimeout()).isEqualTo(300);
    }

    @Test
    public void shouldSetResponseCacheTimeout() {
        int timeout = 400;

        params.setResponseCacheTimeout(timeout);

        assertThat(params.getResponseCacheTimeout()).isEqualTo(timeout);
    }

    @Test
    public void subscriptionsShouldDefaultToCurrentTenant() {
        assertThat(params.getSubscriptions()).isEqualTo(CURRENT_TENANT);
    }

    @Test
    public void shouldSetSubscriptions() {
        ApiSubscriptions subscriptions = ALL_TENANTS;

        params.setSubscriptions(subscriptions);

        assertThat(params.getSubscriptions()).isEqualTo(subscriptions);
    }

    @Test
    public void tenantsShouldDefaultToEmptyList() {
        assertThat(params.getTenants()).isEmpty();
    }

    @Test
    public void shouldSetTenants() {
        params.setTenants("tenant1", "tenant2");

        assertThat(params.getTenants()).containsExactly("tenant1", "tenant2");
    }

}
