package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class StringArgumentBuilderTest {

    private static final String PREFIX = "&name=";

    private final StringArgumentBuilder builder = new StringArgumentBuilder("name");

    @Test
    public void shouldReturnEmptyValueIfNoValuePassed() {
        String result = builder.build(EMPTY);

        assertThat(result).isEqualTo(PREFIX + EMPTY);
    }

    @Test
    public void shouldFormatNameAndValue() {
        String value = "value";

        String result = builder.build(value);

        assertThat(result).isEqualTo(PREFIX + value);
    }

    @Test
    public void shouldFormatNameAndValues() {
        List<String> values = Arrays.asList("value 1", "value 2");

        String result = builder.build(values);

        assertThat(result).isEqualTo(PREFIX + "value+1,value+2");
    }

    @Test
    public void shouldFormatBooleanValue() {
        boolean value = false;

        String result = builder.build(value);

        assertThat(result).isEqualTo(PREFIX + "false");
    }

}
