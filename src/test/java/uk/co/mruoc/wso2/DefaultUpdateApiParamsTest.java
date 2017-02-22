package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiEndpointType.*;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;
import static uk.co.mruoc.wso2.ApiVisibility.*;

public class DefaultUpdateApiParamsTest {

    private final DefaultUpdateApiParams params = new DefaultUpdateApiParams();

    @Test
    public void visibilityShouldDefaultToPublic() {
        assertThat(params.getVisibility()).isEqualTo(PUBLIC);
    }

    @Test
    public void shouldSetVisibility() {
        ApiVisibility visibility = PRIVATE;

        params.setVisibility(visibility);

        assertThat(params.getVisibility()).isEqualTo(visibility);
    }

    @Test
    public void rolesShouldDefaultToEmptyList() {
        assertThat(params.getRoles()).isEmpty();
    }

    @Test
    public void shouldSetRoles() {
        params.setRoles("role1", "role2");

        assertThat(params.getRoles()).containsExactly("role1", "role2");
    }

    @Test
    public void descriptionShouldDefaultToEmpty() {
        assertThat(params.getDescription()).isEmpty();
    }

    @Test
    public void shouldSetDescription() {
        String description = "API description";

        params.setDescription(description);

        assertThat(params.getDescription()).isEqualTo(description);
    }

    @Test
    public void tagsShouldDefaultToEmptyList() {
        assertThat(params.getTags()).isEmpty();
    }

    @Test
    public void shouldSetTags() {
        params.setTags("tag1", "tag2");

        assertThat(params.getTags()).containsExactly("tag1", "tag2");
    }

    @Test
    public void endpointTypeShouldDefaultToUnsecured() {
        assertThat(params.getEndpointType()).isEqualTo(UNSECURED);
    }

    @Test
    public void shouldSetEndpointType() {
        ApiEndpointType endpointType = SECURED;

        params.setEndpointType(endpointType);

        assertThat(params.getEndpointType()).isEqualTo(endpointType);
    }

    @Test
    public void endpointUsernameShouldDefaultToEmpty() {
        assertThat(params.getEndpointUsername()).isEmpty();
    }

    @Test
    public void shouldSetEndpointUsername() {
        String username = "admin";

        params.setEndpointUsername(username);

        assertThat(params.getEndpointUsername()).isEqualTo(username);
    }

    @Test
    public void endpointPasswordShouldDefaultToEmpty() {
        assertThat(params.getEndpointPassword()).isEmpty();
    }

    @Test
    public void shouldSetEndpointPassword() {
        String password = "admin";

        params.setEndpointPassword(password);

        assertThat(params.getEndpointPassword()).isEqualTo(password);
    }

    @Test
    public void tiersShouldDefaultToUnlimited() {
        assertThat(params.getTiers()).containsExactly(UNLIMITED);
    }

    @Test
    public void shouldSetTiers() {
        params.setTiers(GOLD, BRONZE);

        assertThat(params.getTiers()).containsExactly(GOLD, BRONZE);
    }

    @Test
    public void isHttpCheckedShouldDefaultToTrue() {
        assertThat(params.isHttpChecked()).isTrue();
    }

    @Test
    public void shouldSetIsHttpChecked() {
        params.setHttpChecked(false);

        assertThat(params.isHttpChecked()).isFalse();
    }

    @Test
    public void isHttpsCheckedShouldDefaultToTrue() {
        assertThat(params.isHttpsChecked()).isTrue();
    }

    @Test
    public void shouldSetIsHttpsChecked() {
        params.setHttpsChecked(false);

        assertThat(params.isHttpsChecked()).isFalse();
    }

    @Test
    public void endpointConfigShouldDefaultToEmpty() {
        assertThat(params.getEndpointConfig()).isEmpty();
    }

    @Test
    public void shouldSetEndpointConfig() {
        String config = "config";

        params.setEndpointConfig(config);

        assertThat(params.getEndpointConfig()).isEqualTo(config);
    }

    @Test
    public void swaggerShouldDefaultToEmpty() {
        assertThat(params.getSwagger()).isEmpty();
    }

    @Test
    public void shouldSetSwagger() {
        String swagger = "swagger";

        params.setSwagger(swagger);

        assertThat(params.getSwagger()).isEqualTo(swagger);
    }

    @Test
    public void contextShouldDefaultToEmpty() {
        assertThat(params.getContext()).isEmpty();
    }

    @Test
    public void shouldSetContext() {
        String context = "context/v1";

        params.setContext(context);

        assertThat(params.getContext()).isEqualTo(context);
    }

    @Test
    public void thumbShouldDefaultToEmpty() {
        assertThat(params.getThumbnailImagePath()).isEmpty();
    }

    @Test
    public void shouldSetThumbnailImagePath() {
        String thumb = "http://thumbnail/image.png";

        params.setThumbnailImagePath(thumb);

        assertThat(params.getThumbnailImagePath()).isEqualTo(thumb);
    }

}
