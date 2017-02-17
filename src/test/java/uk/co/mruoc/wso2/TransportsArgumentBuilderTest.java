package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TransportsArgumentBuilderTest {

    private static final String HTTP_PREFIX = "http_checked=";
    private static final String HTTPS_PREFIX = "&https_checked=";

    private final TransportsArgumentBuilder builder = new TransportsArgumentBuilder();

    private final TransportParams params = mock(TransportParams.class);

    @Test
    public void shouldBuildBothFalse() {
        given(params.isHttpChecked()).willReturn(false);
        given(params.isHttpsChecked()).willReturn(false);

        assertThat(builder.build(params)).isEqualTo(HTTP_PREFIX + HTTPS_PREFIX);
    }

    @Test
    public void shouldBuildHttpTrue() {
        given(params.isHttpChecked()).willReturn(true);
        given(params.isHttpsChecked()).willReturn(false);

        assertThat(builder.build(params)).isEqualTo(HTTP_PREFIX + "http" + HTTPS_PREFIX);
    }

    @Test
    public void shouldBuildHttpsTrue() {
        given(params.isHttpChecked()).willReturn(false);
        given(params.isHttpsChecked()).willReturn(true);

        assertThat(builder.build(params)).isEqualTo(HTTP_PREFIX + HTTPS_PREFIX + "https");
    }

    @Test
    public void shouldBuildBothTrue() {
        given(params.isHttpChecked()).willReturn(true);
        given(params.isHttpsChecked()).willReturn(true);

        assertThat(builder.build(params)).isEqualTo(HTTP_PREFIX + "http" + HTTPS_PREFIX + "https");
    }

}
