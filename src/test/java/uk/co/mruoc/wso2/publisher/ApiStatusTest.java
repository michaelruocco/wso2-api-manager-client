package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.publisher.ApiStatus.PUBLISHED;
import static uk.co.mruoc.wso2.publisher.ApiStatus.parse;

public class ApiStatusTest {

    @Test
    public void shouldParseNameToStatus() {
        assertThat(parse("PUBlisHED ")).isEqualTo(PUBLISHED);
    }

}
