package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiStatus.CREATED;
import static uk.co.mruoc.wso2.ApiStatus.PUBLISHED;

public class DefaultSetStatusParamsTest {

    private final DefaultSetStatusParams params = new DefaultSetStatusParams();

    @Test
    public void statusShouldDefaultToCreated() {
        assertThat(params.getStatus()).isEqualTo(CREATED);
    }

    @Test
    public void publishToGatewayShouldDefaultToTrue() {
        assertThat(params.isPublishToGateway()).isTrue();
    }

    @Test
    public void requireSubscriptionShouldDefaultToTrue() {
        assertThat(params.isRequireResubscription()).isTrue();
    }

    @Test
    public void shouldStatus() {
        ApiStatus status = PUBLISHED;

        params.setStatus(status);

        assertThat(params.getStatus()).isEqualTo(status);
    }

    @Test
    public void shouldSetPublishToGateway() {
        boolean publishToGateway = false;

        params.setPublishToGateway(publishToGateway);

        assertThat(params.isPublishToGateway()).isEqualTo(publishToGateway);
    }

    @Test
    public void shouldRequireSubscription() {
        boolean requireSubscription = false;

        params.setRequireSubscription(requireSubscription);

        assertThat(params.isRequireResubscription()).isEqualTo(requireSubscription);
    }

}