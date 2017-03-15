package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiSubscriptions.ALL_TENANTS;
import static uk.co.mruoc.wso2.ApiSubscriptions.CURRENT_TENANT;

public class DefaultAddSubscriptionParamsTest {

    private final DefaultAddSubscriptionParams params = new DefaultAddSubscriptionParams();

    @Test
    public void getApplicationNameShouldDefaultToEmpty() {
        assertThat(params.getApplicationName()).isEmpty();
    }

    @Test
    public void shouldSetApplicationName() {
        String applicationName = "applicationName";

        params.setApplicationName(applicationName);

        assertThat(params.getApplicationName()).isEqualTo(applicationName);
    }

    @Test
    public void tierShouldDefaultToUnlimited() {
        assertThat(params.getTier()).isEqualTo(ApiTierAvailability.UNLIMITED);
    }

    @Test
    public void shouldSetTier() {
        ApiTierAvailability tier = ApiTierAvailability.BRONZE;

        params.setTier(tier);

        assertThat(params.getTier()).isEqualTo(tier);
    }

}
