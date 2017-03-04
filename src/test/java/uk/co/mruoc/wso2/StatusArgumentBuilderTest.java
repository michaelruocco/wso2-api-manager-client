package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiStatus.PUBLISHED;

public class StatusArgumentBuilderTest {

    private static final String PREFIX = "&status=";

    private final StatusArgumentBuilder builder = new StatusArgumentBuilder();

    private final SetStatusParams params = mock(SetStatusParams.class);

    @Test
    public void shouldReturnEmptyStringIfNotSet() {
        assertThat(builder.build(params)).isEqualTo(EMPTY);
    }

    @Test
    public void shouldBuildStatus() {
        given(params.getStatus()).willReturn(PUBLISHED);

        assertThat(builder.build(params)).isEqualTo(PREFIX + PUBLISHED.name());
    }

}
