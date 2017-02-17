package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.wso2.DefaultUpdateApiParams.DefaultUpdateApiParamsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiEndpointType.*;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;
import static uk.co.mruoc.wso2.ApiVisibility.*;

public class DefaultUpdateApiParamsTest {

    private final DefaultUpdateApiParamsBuilder builder = new DefaultUpdateApiParamsBuilder();

    @Test
    public void visibilityShouldDefaultToPublic() {
        UpdateApiParams params = builder.build();

        assertThat(params.getVisibility()).isEqualTo(PUBLIC);
    }

    @Test
    public void shouldSetVisibility() {
        ApiVisibility visibility = PRIVATE;

        UpdateApiParams params = builder.setVisibility(visibility).build();

        assertThat(params.getVisibility()).isEqualTo(visibility);
    }

    @Test
    public void rolesShouldDefaultToEmptyList() {
        UpdateApiParams params = builder.build();

        assertThat(params.getRoles()).isEmpty();
    }

    @Test
    public void shouldSetRoles() {
        UpdateApiParams params = builder.setRoles("role1", "role2").build();

        assertThat(params.getRoles()).containsExactly("role1", "role2");
    }

    @Test
    public void shouldSetDescription() {
        String description = "API description";

        UpdateApiParams params = builder.setDescription(description).build();

        assertThat(params.getDescription()).isEqualTo(description);
    }

    @Test
    public void tagsShouldDefaultToEmptyList() {
        UpdateApiParams params = builder.build();

        assertThat(params.getTags()).isEmpty();
    }

    @Test
    public void shouldSetTags() {
        UpdateApiParams params = builder.setTags("tag1", "tag2").build();

        assertThat(params.getTags()).containsExactly("tag1", "tag2");
    }

    @Test
    public void endpointTypeShouldDefaultToUnsecured() {
        UpdateApiParams params = builder.build();

        assertThat(params.getEndpointType()).isEqualTo(UNSECURED);
    }

    @Test
    public void shouldSetEndpointType() {
        ApiEndpointType endpointType = SECURED;

        UpdateApiParams params = builder.setEndpointType(endpointType).build();

        assertThat(params.getEndpointType()).isEqualTo(endpointType);
    }

    @Test
    public void shouldSetEndpointUsername() {
        String username = "admin";

        UpdateApiParams params = builder.setEndpointUsername(username).build();

        assertThat(params.getEndpointUsername()).isEqualTo(username);
    }

    @Test
    public void shouldSetEndpointPassword() {
        String password = "admin";

        UpdateApiParams params = builder.setEndpointPassword(password).build();

        assertThat(params.getEndpointPassword()).isEqualTo(password);
    }

    @Test
    public void tiersShouldDefaultToUnlimited() {
        UpdateApiParams params = builder.build();

        assertThat(params.getTiers()).containsExactly(UNLIMITED);
    }

    @Test
    public void shouldSetTiers() {
        UpdateApiParams params = builder.setTiers(GOLD, BRONZE).build();

        assertThat(params.getTiers()).containsExactly(GOLD, BRONZE);
    }

    @Test
    public void isHttpCheckedShouldDefaultToTrue() {
        UpdateApiParams params = builder.build();

        assertThat(params.isHttpChecked()).isTrue();
    }

    @Test
    public void shouldSetIsHttpChecked() {
        UpdateApiParams params = builder.setIsHttpChecked(false).build();

        assertThat(params.isHttpChecked()).isFalse();
    }

    @Test
    public void isHttpsCheckedShouldDefaultToTrue() {
        UpdateApiParams params = builder.build();

        assertThat(params.isHttpsChecked()).isTrue();
    }

    @Test
    public void shouldSetIsHttpsChecked() {
        UpdateApiParams params = builder.setIsHttpsChecked(false).build();

        assertThat(params.isHttpsChecked()).isFalse();
    }

    @Test
    public void shouldSetEndpointConfig() {
        String config = "config";

        UpdateApiParams params = builder.setEndpointConfig(config).build();

        assertThat(params.getEndpointConfig()).isEqualTo(config);
    }

    @Test
    public void shouldSetSwagger() {
        String swagger = "swagger";

        UpdateApiParams params = builder.setSwagger(swagger).build();

        assertThat(params.getSwagger()).isEqualTo(swagger);
    }

}
