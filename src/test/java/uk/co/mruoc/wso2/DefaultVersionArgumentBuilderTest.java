package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultVersionArgumentBuilderTest {

    private final DefaultVersionArgumentBuilder builder = new DefaultVersionArgumentBuilder();

    private final AddApiParams params = mock(AddApiParams.class);

    @Test
    public void shouldHandleNotDefaultVersion() {
        given(params.isDefaultVersion()).willReturn(false);

        assertThat(builder.build(params)).isEmpty();
    }

    @Test
    public void shouldHandleDefaultVersion() {
        given(params.isDefaultVersion()).willReturn(true);

        assertThat(builder.build(params)).isEqualTo("default_version_checked=default_version");
    }

}
