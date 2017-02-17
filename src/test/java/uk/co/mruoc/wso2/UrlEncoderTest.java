package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlEncoderTest {

    @Test
    public void shouldUrlEncodeString() {
        String value = "a test value { / }";
        String encodedValue = "a+test+value+%7B+%2F+%7D";

        assertThat(UrlEncoder.encode(value)).isEqualTo(encodedValue);
    }

    @Test
    public void shouldUrlEncodeListToCommaSeparatedString() {
        List<String> values = Arrays.asList("value 1", "value 2");
        String encodedValue = "value+1,value+2";

        assertThat(UrlEncoder.encodeToCommaSeparatedList(values)).isEqualTo(encodedValue);
    }

    @Test
    public void shouldHandleNullString() {
        assertThat(UrlEncoder.encode(null)).isEqualTo("");
    }

}
