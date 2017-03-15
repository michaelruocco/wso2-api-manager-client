package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultAddApplicationParamsTest {

    private final DefaultAddApplicationParams params = new DefaultAddApplicationParams();

    @Test
    public void getApplicationNameShouldDefaultToEmpty() {
        assertThat(params.getApplicationName()).isEmpty();
    }

    @Test
    public void shouldSetApplicationName() {
        String applicationName = "application-name";

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

    @Test
    public void getApplicationDescriptionShouldDefaultToEmpty() {
        assertThat(params.getApplicationDescription()).isEmpty();
    }

    @Test
    public void shouldSetApplicationDescription() {
        String applicationDescription = "application-description";

        params.setApplicationDescription(applicationDescription);

        assertThat(params.getApplicationDescription()).isEqualTo(applicationDescription);
    }

    @Test
    public void getCallbackUrlShouldDefaultToEmpty() {
        assertThat(params.getCallbackUrl()).isEmpty();
    }

    @Test
    public void shouldSetCallbackUrl() {
        String callbackUrl = "url";

        params.setCallbackUrl(callbackUrl);

        assertThat(params.getCallbackUrl()).isEqualTo(callbackUrl);
    }

}
