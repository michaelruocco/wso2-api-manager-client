package uk.co.mruoc.wso2;

import org.junit.Test;
import uk.co.mruoc.wso2.DefaultUpdateApiParams.DefaultUpdateApiParamsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;
import static uk.co.mruoc.wso2.ApiVisibility.*;

public class DefaultUpdateApiParamsTest {

    private final DefaultUpdateApiParamsBuilder builder = new DefaultUpdateApiParamsBuilder();

    @Test
    public void shouldSetVisibility() {
        ApiVisibility visibility = PRIVATE;

        UpdateApiParams params = builder.setVisibility(visibility).build();

        assertThat(params.getVisibility()).isEqualTo(visibility);
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
    public void shouldSetTags() {
        UpdateApiParams params = builder.setTags("tag1", "tag2").build();

        assertThat(params.getTags()).containsExactly("tag1", "tag2");
    }

    @Test
    public void shouldSetEndpointType() {
        ApiEndpointType endpointType = ApiEndpointType.SECURED;

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
    public void shouldSetTiers() {
        UpdateApiParams params = builder.setTiers(GOLD, BRONZE).build();

        assertThat(params.getTiers()).containsExactly(GOLD, BRONZE);
    }

    @Test
    public void shouldSetHttpChecked() {
        UpdateApiParams params = builder.setHttpChecked(true).build();

        assertThat(params.isHttpChecked()).isTrue();
    }

    @Test
    public void shouldSetHttpsChecked() {
        UpdateApiParams params = builder.setHttpsChecked(true).build();

        assertThat(params.isHttpsChecked()).isTrue();
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
