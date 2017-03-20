package uk.co.mruoc.wso2.store.addsubscription;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiTierAvailability.GOLD;

public class AddSubscriptionParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=addAPISubscription";

    private AddSubscriptionParamsToQueryStringConverter converter = new AddSubscriptionParamsToQueryStringConverter();

    private AddSubscriptionParams params = mock(AddSubscriptionParams.class);

    @Test
    public void shouldStartWithPrefix() {
        String result = converter.convert(params);

        assertThat(result).startsWith(PREFIX);
    }

    @Test
    public void shouldConvertApplicationName() {
        given(params.getApplicationName()).willReturn("application-name");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&applicationName=application-name");
    }

    @Test
    public void shouldConvertTier() {
        given(params.getTier()).willReturn(GOLD);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&tier=Gold");
    }

    @Test
    public void shouldConvertApiName() {
        given(params.getApiName()).willReturn("api-name");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&name=api-name");
    }

    @Test
    public void shouldConvertApiVersion() {
        given(params.getApiVersion()).willReturn("v1");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&version=v1");
    }

    @Test
    public void shouldConvertApiProvider() {
        given(params.getProvider()).willReturn("admin");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&provider=admin");
    }

}