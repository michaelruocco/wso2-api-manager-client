package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiEndpointType.*;
import static uk.co.mruoc.wso2.ApiSubscriptions.SPECIFIC_TENANTS;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;
import static uk.co.mruoc.wso2.ApiVisibility.*;

public class DefaultAddApiParamsToQueryStringConverterTest {

    private DefaultAddApiParamsToQueryStringConverter converter = new DefaultAddApiParamsToQueryStringConverter();

    private AddApiParams params = mock(AddApiParams.class);

    @Test
    public void shouldConvertName() {
        given(params.getName()).willReturn("api-name");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&name=api-name");
    }

    @Test
    public void shouldConvertContext() {
        given(params.getContext()).willReturn("/api-context");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&context=%2Fapi-context");
    }

    @Test
    public void shouldConvertVersion() {
        given(params.getVersion()).willReturn("v1");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&version=v1");
    }

    @Test
    public void shouldConvertDescription() {
        given(params.getDescription()).willReturn("api description");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&description=api+description");
    }

    @Test
    public void shouldConvertSwagger() {
        given(params.getSwagger()).willReturn("{ swagger: 2.0 }");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&swagger=%7B+swagger%3A+2.0+%7D");
    }

    @Test
    public void shouldConvertEndpointConfiguration() {
        given(params.getEndpointConfig()).willReturn("endpoint configuration");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&endpoint_config=endpoint+configuration");
    }

    @Test
    public void shouldConvertTags() {
        given(params.getTags()).willReturn(Arrays.asList("tag1", "tag 2"));

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&tags=tag1,tag+2");
    }

    @Test
    public void shouldConvertTiers() {
        given(params.getTiers()).willReturn(Arrays.asList(BRONZE, GOLD));

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&tiersCollection=Bronze,Gold");
    }

    @Test
    public void shouldConvertVisibility() {
        given(params.getVisibility()).willReturn(RESTRICTED);
        given(params.getRoles()).willReturn(Arrays.asList("role1", "role 2"));

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&visibility=restricted&roles=role1,role+2");
    }

    @Test
    public void shouldConvertEndpointSecurity() {
        given(params.getEndpointType()).willReturn(SECURED);
        given(params.getEndpointUsername()).willReturn("admin");
        given(params.getEndpointPassword()).willReturn("pass");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&endpointType=secured&epUsername=admin&epPassword=pass");
    }

    @Test
    public void shouldConvertResponseCache() {
        given(params.isResponseCacheEnabled()).willReturn(true);
        given(params.getResponseCacheTimeout()).willReturn(300);

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&responseCache=enabled&cacheTimeout=300");
    }

    @Test
    public void shouldConvertTransports() {
        given(params.isHttpChecked()).willReturn(true);
        given(params.isHttpsChecked()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&http_checked=http&https_checked=https");
    }

    @Test
    public void shouldConvertSequences() {
        given(params.getInSequence()).willReturn("in-sequence");
        given(params.getOutSequence()).willReturn("out-sequence");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&inSequence=in-sequence&outSequence=out-sequence");
    }

    @Test
    public void shouldConvertDefaultVersion() {
        given(params.isDefaultVersion()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&default_version_checked=default_version");
    }

    @Test
    public void shouldConvertSubscriptions() {
        given(params.getSubscriptions()).willReturn(SPECIFIC_TENANTS);
        given(params.getTenants()).willReturn(Arrays.asList("tenant1", "tenant 2"));

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&subscriptions=specific_tenants&tenants=tenant1,tenant+2");
    }

}
