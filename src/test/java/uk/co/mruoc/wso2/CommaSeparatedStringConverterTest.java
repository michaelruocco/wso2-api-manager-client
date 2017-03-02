package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;
import static org.assertj.core.api.Assertions.assertThat;

public class CommaSeparatedStringConverterTest {

    private static final String STRING_VALUE = "value1, value2";
    private static final List<String> LIST_VALUES = Arrays.asList("value1", "value2");

    @Test
    public void shouldConvertListToCommaSeparatedString() {
        assertThat(CommaSeparatedStringConverter.toString(LIST_VALUES)).isEqualTo(deleteWhitespace(STRING_VALUE));
    }

    @Test
    public void shouldConvertCommaSeparatedStringToList() {
        assertThat(CommaSeparatedStringConverter.toList(STRING_VALUE)).containsExactlyElementsOf(LIST_VALUES);
    }

}
