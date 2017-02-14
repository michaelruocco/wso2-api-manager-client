package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetApiParamsToQueryStringConverterTest {

    private static final String NAME = "api-name";
    private static final String VERSION = "v1";
    private static final String PROVIDER = "admin";

    private final GetApiParamsToQueryStringConverter converter = new GetApiParamsToQueryStringConverter();

    @Test
    public void shouldBuildQueryString() {
        GetApiParams params = mock(GetApiParams.class);
        given(params.getName()).willReturn(NAME);
        given(params.getVersion()).willReturn(VERSION);
        given(params.getProvider()).willReturn(PROVIDER);

        String expectedQueryString = "?action=getAPI&name=" + NAME + "&version=" + VERSION + "&provider=" + PROVIDER;

        String result = converter.toQueryString(params);

        assertThat(result).isEqualTo(expectedQueryString);
    }

}
