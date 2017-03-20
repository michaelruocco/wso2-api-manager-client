package uk.co.mruoc.wso2.publisher.getapi;

import org.junit.Test;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.getapi.GetApiParamsToQueryStringConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetApiParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=getAPI";
    private static final String NAME = "api-name";
    private static final String VERSION = "v1";
    private static final String PROVIDER = "admin";

    private final SelectApiParams params = mock(SelectApiParams.class);

    private final GetApiParamsToQueryStringConverter converter = new GetApiParamsToQueryStringConverter();

    @Test
    public void shouldStartWithPrefix() {
        String result = converter.convert(params);

        assertThat(result).startsWith(PREFIX);
    }

    @Test
    public void shouldConvertName() {
        given(params.getApiName()).willReturn(NAME);

        String result = converter.convert(params);

        assertThat(result).contains("&name=" + NAME);
    }

    @Test
    public void shouldConvertVersion() {
        given(params.getApiVersion()).willReturn(VERSION);

        String result = converter.convert(params);

        assertThat(result).contains("&version=" + VERSION);
    }

    @Test
    public void shouldConvertProvider() {
        given(params.getProvider()).willReturn(PROVIDER);

        String result = converter.convert(params);

        assertThat(result).contains("&provider=" + PROVIDER);
    }

}
