package uk.co.mruoc.wso2.store.addapplication;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiTierAvailability.GOLD;

public class AddApplicationParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=addApplication";

    private AddApplicationParamsToQueryStringConverter converter = new AddApplicationParamsToQueryStringConverter();

    private AddApplicationParams params = mock(AddApplicationParams.class);

    @Test
    public void shouldStartWithPrefix() {
        String result = converter.convert(params);

        assertThat(result).startsWith(PREFIX);
    }

    @Test
    public void shouldConvertName() {
        given(params.getApplicationName()).willReturn("application-name");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&application=application-name");
    }

    @Test
    public void shouldConvertTier() {
        given(params.getTier()).willReturn(GOLD);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&tier=Gold");
    }

    @Test
    public void shouldConvertDescription() {
        given(params.getApplicationDescription()).willReturn("application description");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&description=application+description");
    }

    @Test
    public void shouldConvertCallbackUrl() {
        given(params.getCallbackUrl()).willReturn("http://localhost:1234/url");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&callbackUrl=http%3A%2F%2Flocalhost%3A1234%2Furl");
    }

}