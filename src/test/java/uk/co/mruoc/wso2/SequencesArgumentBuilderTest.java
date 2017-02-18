package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SequencesArgumentBuilderTest {

    private static final String IN_PREFIX = "&inSequence=";
    private static final String OUT_PREFIX = "&outSequence=";

    private final SequencesArgumentBuilder builder = new SequencesArgumentBuilder();

    private final SequenceParams params = mock(SequenceParams.class);

    @Test
    public void shouldBuildNoSequences() {
        assertThat(builder.build(params)).isEmpty();
    }

    @Test
    public void shouldBuildSequences() {
        given(params.getInSequence()).willReturn("in sequence");
        given(params.getOutSequence()).willReturn("out sequence");

        assertThat(builder.build(params)).isEqualTo(IN_PREFIX + "in+sequence" + OUT_PREFIX + "out+sequence");
    }

}
