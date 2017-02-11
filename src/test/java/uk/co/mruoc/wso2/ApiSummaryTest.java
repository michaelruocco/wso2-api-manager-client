package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.wso2.ApiSummary.ApiSummaryBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSummaryTest {

    private ApiSummaryBuilder builder = new ApiSummaryBuilder();

    @Test
    public void shouldReturnName() {
        String name = "api-name";

        ApiSummary summary = builder.setName(name).build();

        assertThat(summary.getName()).isEqualTo(name);
    }

    @Test
    public void shouldReturnVersion() {
        String version = "v1";

        ApiSummary summary = builder.setVersion(version).build();

        assertThat(summary.getVersion()).isEqualTo(version);
    }

    @Test
    public void shouldReturnProvider() {
        String provider = "admin";

        ApiSummary summary = builder.setProvider(provider).build();

        assertThat(summary.getProvider()).isEqualTo(provider);
    }

    @Test
    public void shouldReturnStatus() {
        String status = "CREATED";

        ApiSummary summary = builder.setStatus(status).build();

        assertThat(summary.getStatus()).isEqualTo(status);
    }

    @Test
    public void shouldReturnThumbnailImagePath() {
        String thumbnailImagePath = "/path/image.png";

        ApiSummary summary = builder.setThumbnailImagePath(thumbnailImagePath).build();

        assertThat(summary.getThumbnailImagePath()).isEqualTo(thumbnailImagePath);
    }

    @Test
    public void shouldReturnSubscriberCount() {
        int subscriberCount = 3;

        ApiSummary summary = builder.setSubscriberCount(subscriberCount).build();

        assertThat(summary.getSubscriberCount()).isEqualTo(subscriberCount);
    }

}
