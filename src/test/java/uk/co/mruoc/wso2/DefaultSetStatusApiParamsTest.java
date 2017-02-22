package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultSetStatusApiParamsTest {

    private final DefaultSetStatusApiParams params = new DefaultSetStatusApiParams();

    @Test
    public void statusShouldDefaultToEmptyString() {
        assertThat(params.getStatus()).isEmpty();
    }

    @Test
    public void publishToGatewayShouldDefaultToTrue() {
        assertThat(params.isPublishToGateway()).isTrue();
    }

    @Test
    public void requireSubscriptionShouldDefaultToTrue() {
        assertThat(params.isRequireSubscription()).isTrue();
    }

    @Test
    public void shouldStatus() {
        String status = "PUBLISHED";

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

        assertThat(params.isRequireSubscription()).isEqualTo(requireSubscription);
    }

}
