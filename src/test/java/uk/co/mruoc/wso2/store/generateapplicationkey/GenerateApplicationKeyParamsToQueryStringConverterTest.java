package uk.co.mruoc.wso2.store.generateapplicationkey;

import org.junit.Test;

import java.net.URLEncoder;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GenerateApplicationKeyParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=generateApplicationKey";

    private GenerateApplicationKeyParamsToQueryStringConverter converter = new GenerateApplicationKeyParamsToQueryStringConverter();

    private GenerateApplicationKeyParams params = mock(GenerateApplicationKeyParams.class);

    @Test
    public void shouldStartWithPrefix() {
        String result = converter.convert(params);

        assertThat(result).startsWith(PREFIX);
    }

    @Test
    public void shouldConvertApplicationName() {
        given(params.getApplicationName()).willReturn("application-name");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&application=application-name");
    }

    @Test
    public void shouldConvertKeyType() {
        given(params.getKeyType()).willReturn(ApiKeyType.PRODUCTION);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&keytype=PRODUCTION");
    }

    @Test
    public void shouldConvertCallbackUrl() {
        given(params.getCallbackUrl()).willReturn("http://localhost:8080/callback");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&callbackUrl=");
    }

    @Test
    public void shouldConvertAuthorizedDomains() {
        given(params.getAuthorizedDomains()).willReturn(Arrays.asList("DOMAIN1", " DOMAIN2"));

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&authorizedDomains=DOMAIN1,DOMAIN2");
    }

    @Test
    public void shouldConvertValidityTime() {
        given(params.getValidityTimeInSeconds()).willReturn(-1);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&validityTime=-1");
    }

    @Test
    public void shouldAppendTokenScope() {
        String queryString = converter.convert(params);

        assertThat(queryString).endsWith("&tokenScope");
    }

}