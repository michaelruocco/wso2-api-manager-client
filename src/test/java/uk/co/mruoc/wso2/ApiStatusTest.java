package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiStatus.PUBLISHED;
import static uk.co.mruoc.wso2.ApiStatus.parse;

public class ApiStatusTest {

    @Test
    public void shouldParseNameToStatus() {
        assertThat(parse("PUBlisHED ")).isEqualTo(PUBLISHED);
    }

}
