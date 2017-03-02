package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiSubscriptions.ALL_TENANTS;
import static uk.co.mruoc.wso2.ApiSubscriptions.parse;

public class ApiSubscriptionsTest {

    @Test
    public void shouldParseNameToSubscriptions() {
        assertThat(parse("all_TENANTS ")).isEqualTo(ALL_TENANTS);
    }

}
