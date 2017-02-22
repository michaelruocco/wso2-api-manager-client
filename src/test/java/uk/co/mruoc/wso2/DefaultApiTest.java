package uk.co.mruoc.wso2;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    @Test
    public void nameShouldDefaultToEmpty() {
        assertThat(api.getName()).isEmpty();
    }

    @Test
    public void shouldReturnName() {
        String name = "api-name";

        api.setName(name);

        assertThat(api.getName()).isEqualTo(name);
    }

    @Test
    public void versionShouldDefaultToEmpty() {
        assertThat(api.getVersion()).isEmpty();
    }

    @Test
    public void shouldReturnVersion() {
        String version = "v1";

        api.setVersion(version);

        assertThat(api.getVersion()).isEqualTo(version);
    }

    @Test
    public void descriptionShouldDefaultToEmpty() {
        assertThat(api.getDescription()).isEmpty();
    }

    @Test
    public void shouldReturnDescription() {
        String description = "A test API";

        api.setDescription(description);

        assertThat(api.getDescription()).isEqualTo(description);
    }

    @Test
    public void contextShouldDefaultToEmpty() {
        assertThat(api.getContext()).isEmpty();
    }

    @Test
    public void shouldReturnContext() {
        String context = "/product/v1";

        api.setContext(context);

        assertThat(api.getContext()).isEqualTo(context);
    }

    @Test
    public void lastUpdatedShouldDefaultToMinLongDate() {
        assertThat(api.getLastUpdated()).isEqualTo(new DateTime(Long.MIN_VALUE));
    }

    @Test
    public void shouldReturnLastUpdated() {
        DateTime lastUpdated = new DateTime();

        api.setLastUpdated(lastUpdated);

        assertThat(api.getLastUpdated()).isEqualTo(lastUpdated);
    }

    @Test
    public void subscriberCountShouldDefaultToZero() {
        assertThat(api.getSubscriberCount()).isEqualTo(0);
    }

    @Test
    public void shouldReturnSubscriberCount() {
        int subscriberCount = 2;

        api.setSubscriberCount(subscriberCount);

        assertThat(api.getSubscriberCount()).isEqualTo(subscriberCount);
    }

    @Test
    public void providerShouldDefaultToEmpty() {
        assertThat(api.getProvider()).isEmpty();
    }

    @Test
    public void shouldReturnProvider() {
        String provider = "admin";

        api.setProvider(provider);

        assertThat(api.getProvider()).isEqualTo(provider);
    }

    @Test
    public void visibilityShouldDefaultToPublic() {
        assertThat(api.getVisibility()).isEqualTo(ApiVisibility.PUBLIC);
    }

    @Test
    public void shouldReturnVisibility() {
        ApiVisibility visibility = ApiVisibility.PRIVATE;

        api.setVisibility(visibility);

        assertThat(api.getVisibility()).isEqualTo(visibility);
    }

    @Test
    public void statusShouldDefaultToCreated() {
        assertThat(api.getStatus()).isEqualTo(ApiStatus.CREATED);
    }

    @Test
    public void shouldReturnStatus() {
        ApiStatus status = ApiStatus.PUBLISHED;

        api.setStatus(status);

        assertThat(api.getStatus()).isEqualTo(status);
    }

    @Test
    public void thumbnailImageUrlShouldDefaultToEmpty() {
        assertThat(api.getThumbnailImageUrl()).isEmpty();
    }

    @Test
    public void shouldReturnThumbnailImageUrl() {
        String thumbnailImageUrl = "http://image/url.png";

        api.setThumbnailImageUrl(thumbnailImageUrl);

        assertThat(api.getThumbnailImageUrl()).isEqualTo(thumbnailImageUrl);
    }

    @Test
    public void tagsShouldDefaultToEmptyList() {
        assertThat(api.getTags()).isEmpty();
    }

    @Test
    public void shouldReturnTags() {
        api.setTags("tag1", "tag2");

        assertThat(api.getTags()).containsExactly("tag1", "tag2");
    }

    @Test
    public void endpointTypeShouldDefaultToUnsecured() {
        assertThat(api.getEndpointType()).isEqualTo(ApiEndpointType.UNSECURED);
    }

    @Test
    public void shouldReturnEndpointType() {
        ApiEndpointType type = ApiEndpointType.SECURED;

        api.setEndpointType(type);

        assertThat(api.getEndpointType()).isEqualTo(type);
    }

    @Test
    public void endpointUsernameShouldDefaultToEmpty() {
        assertThat(api.getEndpointUsername()).isEmpty();
    }

    @Test
    public void shouldReturnEndpointUsername() {
        String username = "username";

        api.setEndpointUsername(username);

        assertThat(api.getEndpointUsername()).isEqualTo(username);
    }

    @Test
    public void endpointPasswordShouldDefaultToEmpty() {
        assertThat(api.getEndpointPassword()).isEmpty();
    }

    @Test
    public void shouldReturnEndpointPassword() {
        String password = "pass";

        api.setEndpointPassword(password);

        assertThat(api.getEndpointPassword()).isEqualTo(password);
    }

    @Test
    public void endpointConfigShouldDefaultToEmpty() {
        assertThat(api.getEndpointConfig()).isEmpty();
    }

    @Test
    public void shouldReturnEndpointConfig() {
        String config = "{ config }";

        api.setEndpointConfig(config);

        assertThat(api.getEndpointConfig()).isEqualTo(config);
    }

    @Test
    public void httpCheckedShouldDefaultToTrue() {
        assertThat(api.isHttpChecked()).isTrue();
    }

    @Test
    public void shouldReturnHttpChecked() {
        api.setHttpChecked(false);

        assertThat(api.isHttpChecked()).isFalse();
    }

    @Test
    public void httpsCheckedShouldDefaultToTrue() {
        assertThat(api.isHttpsChecked()).isTrue();
    }

    @Test
    public void shouldReturnHttpsChecked() {
        api.setHttpsChecked(false);

        assertThat(api.isHttpsChecked()).isFalse();
    }

    @Test
    public void tiersShouldDefaultToUnlimited() {
        assertThat(api.getTiers()).containsExactly(ApiTierAvailability.UNLIMITED);
    }

    @Test
    public void shouldReturnTiers() {
        api.setTiers(ApiTierAvailability.GOLD, ApiTierAvailability.BRONZE);

        assertThat(api.getTiers()).containsExactly(ApiTierAvailability.GOLD,ApiTierAvailability.BRONZE);
    }

}
