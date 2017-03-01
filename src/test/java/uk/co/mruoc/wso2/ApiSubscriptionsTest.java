package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiSubscriptions.ALL_TENANTS;
import static uk.co.mruoc.wso2.ApiSubscriptions.CURRENT_TENANT;
import static uk.co.mruoc.wso2.ApiSubscriptions.toSubscriptionsList;

public class ApiSubscriptionsTest {

    @Test
    public void shouldConvertListOfNamesToListOfSubscriptions() {
        List<String> inputs = Arrays.asList("current_tenant", "ALL_TENANTS ");

        List<ApiSubscriptions> result = toSubscriptionsList(inputs);

        assertThat(result).containsExactly(CURRENT_TENANT, ALL_TENANTS);
    }

}
