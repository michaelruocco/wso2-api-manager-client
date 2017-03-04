package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;

public class ApiToUpdateApiParamsConverterTest {

    private final Api api = mock(Api.class);

    private final ApiToUpdateApiParamsConverter converter = new ApiToUpdateApiParamsConverter();

    @Test
    public void shouldPopulateApiName() {
        String apiName = "api-name";
        given(api.getName()).willReturn(apiName);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getApiName()).isEqualTo(apiName);
    }

    @Test
    public void shouldPopulateVersion() {
        String version = "v1";
        given(api.getVersion()).willReturn(version);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getApiVersion()).isEqualTo(version);
    }

    @Test
    public void shouldPopulateContext() {
        String context = "/product/{version}";
        given(api.getContext()).willReturn(context);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getContext()).isEqualTo(context);
    }

    @Test
    public void shouldPopulateProvider() {
        String provider = "admin";
        given(api.getProvider()).willReturn(provider);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getProvider()).isEqualTo(provider);
    }

    @Test
    public void shouldPopulateRoles() {
        List<String> roles = Arrays.asList("role1", "role2");
        given(api.getRoles()).willReturn(roles);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getRoles()).isEqualTo(roles);
    }

    @Test
    public void shouldPopulateTags() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        given(api.getTags()).willReturn(tags);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getTags()).isEqualTo(tags);
    }

    @Test
    public void shouldPopulateTiers() {
        List<ApiTierAvailability> tiers = Arrays.asList(GOLD, BRONZE);
        given(api.getTiers()).willReturn(tiers);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getTiers()).isEqualTo(tiers);
    }

    @Test
    public void shouldPopulateHttpChecked() {
        given(api.isHttpChecked()).willReturn(true);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.isHttpChecked()).isTrue();
    }

    @Test
    public void shouldPopulateHttpsChecked() {
        given(api.isHttpsChecked()).willReturn(true);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.isHttpsChecked()).isTrue();
    }

    @Test
    public void shouldPopulateEndpointType() {
        ApiEndpointType endpointType = ApiEndpointType.SECURED;
        given(api.getEndpointType()).willReturn(endpointType);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getEndpointType()).isEqualTo(endpointType);
    }

    @Test
    public void shouldPopulateEndpointUsername() {
        String username = "username";
        given(api.getEndpointUsername()).willReturn(username);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getEndpointUsername()).isEqualTo(username);
    }

    @Test
    public void shouldPopulateEndpointPassword() {
        String password = "password";
        given(api.getEndpointPassword()).willReturn(password);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getEndpointPassword()).isEqualTo(password);
    }

    @Test
    public void shouldPopulateVisibility() {
        ApiVisibility visibility = ApiVisibility.PRIVATE;
        given(api.getVisibility()).willReturn(visibility);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getVisibility()).isEqualTo(visibility);
    }

    @Test
    public void shouldPopulateEndpointConfig() {
        String config = "config";
        given(api.getEndpointConfig()).willReturn(config);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getEndpointConfig()).isEqualTo(config);
    }

    @Test
    public void shouldPopulateThumbnailImageUrl() {
        String url = "url";
        given(api.getThumbnailImageUrl()).willReturn(url);

        UpdateApiParams params = converter.convert(api);

        assertThat(params.getThumbnailImageUrl()).isEqualTo(url);
    }

}
