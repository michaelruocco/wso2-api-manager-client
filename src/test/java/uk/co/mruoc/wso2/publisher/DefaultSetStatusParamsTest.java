package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.publisher.ApiStatus.CREATED;
import static uk.co.mruoc.wso2.publisher.ApiStatus.PUBLISHED;

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
    public void requireResubscriptionShouldDefaultToFalse() {
        assertThat(params.isRequireResubscription()).isFalse();
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

        params.setRequireResubscription(requireSubscription);

        assertThat(params.isRequireResubscription()).isEqualTo(requireSubscription);
    }

}
