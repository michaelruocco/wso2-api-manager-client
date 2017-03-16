package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.publisher.ApiEndpointType.SECURED;
import static uk.co.mruoc.wso2.ApiTierAvailability.BRONZE;
import static uk.co.mruoc.wso2.ApiTierAvailability.GOLD;
import static uk.co.mruoc.wso2.publisher.ApiVisibility.RESTRICTED;

public class UpdateApiParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=updateAPI";

    private UpdateApiParamsToQueryStringConverter converter = new UpdateApiParamsToQueryStringConverter();

    private UpdateApiParams params = mock(UpdateApiParams.class);

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

    @Test
    public void shouldConvertDescription() {
        given(params.getApiDescription()).willReturn("api description");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&description=api+description");
    }

    @Test
    public void shouldConvertSwagger() {
        given(params.getSwagger()).willReturn("{ swagger: 2.0 }");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&swagger=%7B+swagger%3A+2.0+%7D");
    }

    @Test
    public void shouldConvertEndpointConfiguration() {
        given(params.getEndpointConfig()).willReturn("endpoint configuration");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&endpoint_config=endpoint+configuration");
    }

    @Test
    public void shouldConvertTags() {
        given(params.getTags()).willReturn(Arrays.asList("tag1", "tag 2"));

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&tags=tag1,tag+2");
    }

    @Test
    public void shouldConvertTiers() {
        given(params.getTiers()).willReturn(Arrays.asList(BRONZE, GOLD));

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&tiersCollection=Bronze,Gold");
    }

    @Test
    public void shouldConvertVisibility() {
        given(params.getVisibility()).willReturn(RESTRICTED);
        given(params.getRoles()).willReturn(Arrays.asList("role1", "role 2"));

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&visibility=restricted&roles=role1,role+2");
    }

    @Test
    public void shouldConvertEndpointSecurity() {
        given(params.getEndpointType()).willReturn(SECURED);
        given(params.getEndpointUsername()).willReturn("admin");
        given(params.getEndpointPassword()).willReturn("pass");

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&endpointType=secured&epUsername=admin&epPassword=pass");
    }

    @Test
    public void shouldConvertTransports() {
        given(params.isHttpChecked()).willReturn(true);
        given(params.isHttpsChecked()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&http_checked=http&https_checked=https");
    }

}
