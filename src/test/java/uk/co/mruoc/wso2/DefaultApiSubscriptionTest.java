package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultApiSubscriptionTest {

    private final DefaultApiSubscription subscription = new DefaultApiSubscription();

    @Test
    public void getApplicationNameShouldDefaultToEmpty() {
        assertThat(subscription.getApplicationName()).isEmpty();
    }

    @Test
    public void shouldSetApplicationName() {
        String applicationName = "applicationName";

        subscription.setApplicationName(applicationName);

        assertThat(subscription.getApplicationName()).isEqualTo(applicationName);
    }

    @Test
    public void getApplicationIdShouldDefaultToZero() {
        assertThat(subscription.getApplicationId()).isEqualTo(0);
    }

    @Test
    public void shouldSetApplicationId() {
        int id = 10;

        subscription.setApplicationId(id);

        assertThat(subscription.getApplicationId()).isEqualTo(id);
    }

}
