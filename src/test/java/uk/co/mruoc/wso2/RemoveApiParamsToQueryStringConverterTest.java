package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RemoveApiParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=removeAPI";

    private RemoveApiParamsToQueryStringConverter converter = new RemoveApiParamsToQueryStringConverter();

    private SelectApiParams params = mock(SelectApiParams.class);

    @Test
    public void shouldStartWithPrefix() {
        String result = converter.convert(params);

        assertThat(result).startsWith(PREFIX);
    }

    @Test
    public void shouldConvertName() {
        given(params.getApiName()).willReturn("api-name");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&name=api-name");
    }

    @Test
    public void shouldConvertVersion() {
        given(params.getApiVersion()).willReturn("v1");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&version=v1");
    }

    @Test
    public void shouldConvertProvider() {
        given(params.getProvider()).willReturn("admin");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&provider=admin");
    }

}
