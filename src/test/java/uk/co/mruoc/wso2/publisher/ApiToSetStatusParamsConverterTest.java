package uk.co.mruoc.wso2.publisher;

import org.junit.Test;
import uk.co.mruoc.wso2.publisher.getapi.Api;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ApiToSetStatusParamsConverterTest {

    private final Api api = mock(Api.class);

    private final ApiToSetStatusParamsConverter converter = new ApiToSetStatusParamsConverter();

    @Test
    public void shouldPopulateApiName() {
        String apiName = "api-name";
        given(api.getName()).willReturn(apiName);

        SetStatusParams params = converter.convert(api);

        assertThat(params.getApiName()).isEqualTo(apiName);
    }

    @Test
    public void shouldPopulateVersion() {
        String version = "v1";
        given(api.getVersion()).willReturn(version);

        SetStatusParams params = converter.convert(api);

        assertThat(params.getApiVersion()).isEqualTo(version);
    }

    @Test
    public void shouldPopulateProvider() {
        String provider = "admin";
        given(api.getProvider()).willReturn(provider);

        SetStatusParams params = converter.convert(api);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

}
