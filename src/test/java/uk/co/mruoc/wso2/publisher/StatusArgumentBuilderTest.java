package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.publisher.ApiStatus.PUBLISHED;

public class StatusArgumentBuilderTest {

    private static final String PREFIX = "&status=";

    private final StatusArgumentBuilder builder = new StatusArgumentBuilder();

    private final SetStatusParams params = mock(SetStatusParams.class);

    @Test
    public void shouldReturnEmptyStatusIfNotSet() {
        assertThat(builder.build(params)).isEqualTo(PREFIX);
    }

    @Test
    public void shouldBuildStatus() {
        given(params.getStatus()).willReturn(PUBLISHED);

        assertThat(builder.build(params)).isEqualTo(PREFIX + PUBLISHED.name());
    }

}
