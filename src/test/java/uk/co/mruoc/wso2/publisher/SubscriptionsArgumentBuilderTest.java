package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.publisher.ApiSubscriptions.ALL_TENANTS;
import static uk.co.mruoc.wso2.publisher.ApiSubscriptions.CURRENT_TENANT;
import static uk.co.mruoc.wso2.publisher.ApiSubscriptions.SPECIFIC_TENANTS;

public class SubscriptionsArgumentBuilderTest {

    private static final String PREFIX = "&subscriptions=";

    private final SubscriptionsArgumentBuilder builder = new SubscriptionsArgumentBuilder();

    private final SubscriptionsParams params = mock(SubscriptionsParams.class);

    @Test
    public void shouldBuildCurrentTenant() {
        given(params.getSubscriptions()).willReturn(CURRENT_TENANT);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "current_tenant");
    }

    @Test
    public void shouldBuildAllTenants() {
        given(params.getSubscriptions()).willReturn(ALL_TENANTS);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "all_tenants");
    }

    @Test
    public void shouldBuildSpecificTenantsWithNonSpecified() {
        given(params.getSubscriptions()).willReturn(SPECIFIC_TENANTS);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "specific_tenants&tenants=");
    }

    @Test
    public void shouldBuildSpecificTenants() {
        given(params.getSubscriptions()).willReturn(SPECIFIC_TENANTS);
        given(params.getTenants()).willReturn(Arrays.asList("tenant 1", "tenant 2"));

        assertThat(builder.build(params)).isEqualTo(PREFIX + "specific_tenants&tenants=tenant+1,tenant+2");
    }

}
