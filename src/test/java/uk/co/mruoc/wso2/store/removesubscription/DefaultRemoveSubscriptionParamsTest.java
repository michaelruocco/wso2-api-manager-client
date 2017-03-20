package uk.co.mruoc.wso2.store.removesubscription;

import org.junit.Test;
import uk.co.mruoc.wso2.store.removeapplication.DefaultRemoveSubscriptionParams;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultRemoveSubscriptionParamsTest {

    private final DefaultRemoveSubscriptionParams params = new DefaultRemoveSubscriptionParams();

    @Test
    public void getApplicationNameShouldDefaultToEmpty() {
        assertThat(params.getApplicationName()).isEmpty();
    }

    @Test
    public void shouldSetApplicationName() {
        String applicationName = "applicationName";

        params.setApplicationName(applicationName);

        assertThat(params.getApplicationName()).isEqualTo(applicationName);
    }

}
