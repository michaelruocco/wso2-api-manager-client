package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiEndpointTypeTest {

    @Test
    public void shouldParseApiEndpointType() {
        assertThat(ApiEndpointType.parse("unsecured ")).isEqualTo(ApiEndpointType.UNSECURED);
    }
}
