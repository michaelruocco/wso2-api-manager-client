package uk.co.mruoc.wso2.store.generateapplicationkey;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.store.generateapplicationkey.ApiKeyType.PRODUCTION;
import static uk.co.mruoc.wso2.store.generateapplicationkey.ApiKeyType.SANDBOX;

public class DefaultGenerateApplicationKeyParamsTest {

    private final DefaultGenerateApplicationKeyParams params = new DefaultGenerateApplicationKeyParams();

    @Test
    public void applicationNameShouldDefaultToEmpty() {
        assertThat(params.getApplicationName()).isEmpty();
    }

    @Test
    public void shouldSetApplicationName() {
        String applicationName = "application-name";

        params.setApplicationName(applicationName);

        assertThat(params.getApplicationName()).isEqualTo(applicationName);
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
    public void callbackUrlShouldDefaultToEmpty() {
        assertThat(params.getCallbackUrl()).isEmpty();
    }

    @Test
    public void shouldSetCallbackUrl() {
        String callbackUrl = "url";

        params.setCallbackUrl(callbackUrl);

        assertThat(params.getCallbackUrl()).isEqualTo(callbackUrl);
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
