package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ResponseCacheArgumentBuilderTest {

    private static final String PREFIX = "responseCache=";

    private final ResponseCacheArgumentBuilder builder = new ResponseCacheArgumentBuilder();

    private final ResponseCacheParams params = mock(ResponseCacheParams.class);

    @Test
    public void shouldBuildResponseCacheDisabled() {
        given(params.isResponseCacheEnabled()).willReturn(false);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "disabled");
    }

    @Test
    public void shouldBuildResponseCacheEnabled() {
        given(params.isResponseCacheEnabled()).willReturn(true);
        given(params.getResponseCacheTimeout()).willReturn(300);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "enabled&cacheTimeout=300");
    }

}
