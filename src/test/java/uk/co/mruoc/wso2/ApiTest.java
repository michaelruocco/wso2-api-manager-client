package uk.co.mruoc.wso2;

import org.joda.time.DateTime;
import org.junit.Test;
import uk.co.mruoc.wso2.Api.ApiBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {

    private final ApiBuilder builder = new ApiBuilder();

    @Test
    public void shouldReturnName() {
        String name = "api-name";

        Api api = builder.setName(name).build();

        assertThat(api.getName()).isEqualTo(name);
    }

    @Test
    public void shouldReturnVersion() {
        String version = "v1";

        Api api = builder.setVersion(version).build();

        assertThat(api.getVersion()).isEqualTo(version);
    }

    @Test
    public void shouldReturnDescription() {
        String description = "A test API";

        Api api = builder.setDescription(description).build();

        assertThat(api.getDescription()).isEqualTo(description);
    }

    @Test
    public void shouldReturnContext() {
        String context = "/product/v1";

        Api api = builder.setContext(context).build();

        assertThat(api.getContext()).isEqualTo(context);
    }

    @Test
    public void shouldReturnLastUpdated() {
        DateTime lastUpdated = new DateTime();

        Api api = builder.setLastUpdated(lastUpdated).build();

        assertThat(api.getLastUpdated()).isEqualTo(lastUpdated);
    }

    @Test
    public void shouldReturnSubscriberCount() {
        int subscriberCount = 2;

        Api api = builder.setSubscriberCount(subscriberCount).build();

        assertThat(api.getSubscriberCount()).isEqualTo(subscriberCount);
    }

}
