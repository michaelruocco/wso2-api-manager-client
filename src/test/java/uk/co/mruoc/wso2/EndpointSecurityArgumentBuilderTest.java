package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiEndpointType.SECURED;
import static uk.co.mruoc.wso2.ApiEndpointType.UNSECURED;

public class EndpointSecurityArgumentBuilderTest {

    private static final String PREFIX = "endpointSecurity=";

    private final EndpointSecurityArgumentBuilder builder = new EndpointSecurityArgumentBuilder();

    private final EndpointSecurityParams params = mock(EndpointSecurityParams.class);

    @Test
    public void shouldBuildUnsecured() {
        given(params.getEndpointType()).willReturn(UNSECURED);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "unsecured");
    }

    @Test
    public void shouldBuildSecuredWithNoCredentials() {
        given(params.getEndpointType()).willReturn(SECURED);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "secured&epUsername=&epPassword=");
    }

    @Test
    public void shouldBuildSecuredWithCredentials() {
        given(params.getEndpointType()).willReturn(SECURED);
        given(params.getEndpointUsername()).willReturn("admin");
        given(params.getEndpointPassword()).willReturn("pass word");

        assertThat(builder.build(params)).isEqualTo(PREFIX + "secured&epUsername=admin&epPassword=pass+word");
    }

}
