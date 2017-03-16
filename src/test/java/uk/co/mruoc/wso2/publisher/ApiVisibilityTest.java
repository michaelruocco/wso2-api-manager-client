package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiVisibilityTest {

    @Test
    public void shouldParseApiVisibility() {
        assertThat(ApiVisibility.parse("public ")).isEqualTo(ApiVisibility.PUBLIC);
    }

}
