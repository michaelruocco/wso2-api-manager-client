package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ListToCommaSeparatedStringConverter.toCommaSeparatedString;

public class ListToCommaSeparatedStringConverterTest {

    @Test
    public void shouldConvertListToCommaSeparatedString() {
        List<String> values = Arrays.asList("value1", "value2");
        assertThat(toCommaSeparatedString(values)).isEqualTo("value1,value2");
    }
    
}
