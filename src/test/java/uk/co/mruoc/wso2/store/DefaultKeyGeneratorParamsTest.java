package uk.co.mruoc.wso2.store;

import org.junit.Test;
import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApiKeyType;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.store.generateapplicationkey.ApiKeyType.PRODUCTION;
import static uk.co.mruoc.wso2.store.generateapplicationkey.ApiKeyType.SANDBOX;

public class DefaultKeyGeneratorParamsTest {

    private final DefaultKeyGeneratorParams params = new DefaultKeyGeneratorParams();

    @Test
    public void apiNameShouldDefaultToEmptyString() {
        assertThat(params.getApiName()).isEmpty();
    }

    @Test
    public void shouldSetApiName() {
        String name = "api-name";

        params.setApiName(name);

        assertThat(params.getApiName()).isEqualTo(name);
    }

    @Test
    public void apiVersionShouldDefaultToEmptyString() {
        assertThat(params.getApiVersion()).isEmpty();
    }

    @Test
    public void shouldSetApiVersion() {
        String version = "v1";

        params.setApiVersion(version);

        assertThat(params.getApiVersion()).isEqualTo(version);
    }

    @Test
    public void providerShouldDefaultToEmptyString() {
        assertThat(params.getProvider()).isEmpty();
    }

    @Test
    public void shouldSetProvider() {
        String provider = "admin";

        params.setProvider(provider);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

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

    @Test
    public void keyTypeShouldDefaultToProduction() {
        assertThat(params.getKeyType()).isEqualTo(PRODUCTION);
    }

    @Test
    public void shouldSetKeyType() {
        ApiKeyType keyType = SANDBOX;

        params.setKeyType(keyType);

        assertThat(params.getKeyType()).isEqualTo(keyType);
    }

    @Test
    public void authorizedDomainsShouldDefaultToFalse() {
        assertThat(params.getAuthorizedDomains()).containsExactly("ALL");
    }

    @Test
    public void shouldSetAuthorizedDomains() {
        params.setAuthorizedDomains("domain1", "domain2");

        assertThat(params.getAuthorizedDomains()).containsExactly("domain1", "domain2");
    }

    @Test
    public void validityTimeShouldDefaultToMinusOne() {
        assertThat(params.getValidityTimeInSeconds()).isEqualTo(-1);
    }

    @Test
    public void shouldSetValidityTime() {
        int validityTimeInSeconds = 100;

        params.setValidityTimeInSeconds(validityTimeInSeconds);

        assertThat(params.getValidityTimeInSeconds()).isEqualTo(validityTimeInSeconds);
    }

}
