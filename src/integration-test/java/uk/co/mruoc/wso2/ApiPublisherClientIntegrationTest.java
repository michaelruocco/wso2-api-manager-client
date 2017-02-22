package uk.co.mruoc.wso2;

import org.junit.*;
import org.testcontainers.containers.GenericContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPublisherClientIntegrationTest {

    private static final int CONTAINER_START_TIMEOUT = 60000;
    private static final String BASE_URL = "https://localhost:9443";
    private static final String USERNAME = "admin";

    private final Wso2ContainerStartupChecker startupChecker = new Wso2ContainerStartupChecker(CONTAINER_START_TIMEOUT);

    @Rule
    public final GenericContainer container = new Wso2Container("michaelruocco/wso2am:1.9.1")
            .withExposedPorts(9443, 9763)
            .withLogConsumer(startupChecker);

    private final Credentials credentials = new Credentials(USERNAME, "admin");
    private final ApiPublisherClient client = new DefaultApiPublisherClient(BASE_URL);

    @Before
    public void setUp() {
        startupChecker.waitForContainerToStart();
        client.login(credentials);
    }

    @Test
    public void listAllShouldReturnNoSummariesIfNoApisDeployed() {
        List<ApiSummary> summaries = client.listAllApis();

        assertThat(summaries).isEmpty();
    }

    @Test
    public void shouldAddApi() {
        AddApiParams params = StubAddApiParamsBuilder.build();

        assertThat(client.addApi(params)).isTrue();
    }

    @Test
    public void shouldReturnApiExists() {
        AddApiParams params = StubAddApiParamsBuilder.build();

        client.addApi(params);

        assertThat(client.apiExists(params.getName())).isTrue();
    }

    @Test
    public void shouldRemoveApi() {
        AddApiParams addApiParams = StubAddApiParamsBuilder.build();
        client.addApi(addApiParams);
        assertThat(client.apiExists(addApiParams.getName())).isTrue();

        SelectApiParams selectApiParams = toSelectParams(addApiParams);
        client.removeApi(selectApiParams);
        assertThat(client.apiExists(addApiParams.getName())).isFalse();
    }

    @Test
    public void shouldUpdateApi() {
        String updatedDescription = "updatedDescription";

        AddApiParams addParams = StubAddApiParamsBuilder.build();
        client.addApi(addParams);

        SelectApiParams selectApiParams = toSelectParams(addParams);
        Api api = client.getApi(selectApiParams);

        DefaultUpdateApiParams updateParams = toUpdateParams(api);
        updateParams.setDescription(updatedDescription);
        updateParams.setSwagger(addParams.getSwagger());
        client.updateApi(updateParams);

        Api updatedApi = client.getApi(selectApiParams);

        assertThat(updatedApi.getDescription()).isEqualTo(updatedDescription);
    }

    @After
    public void tearDown() {
        client.logout();
    }

    private SelectApiParams toSelectParams(AddApiParams addParams) {
        DefaultSelectApiParams selectParams = new DefaultSelectApiParams();
        selectParams.setName(addParams.getName());
        selectParams.setVersion(addParams.getVersion());
        selectParams.setProvider(USERNAME);
        return selectParams;
    }

    private DefaultUpdateApiParams toUpdateParams(Api api) {
        DefaultUpdateApiParams updateParams = new DefaultUpdateApiParams();
        updateParams.setName(api.getName());
        updateParams.setVersion(api.getVersion());
        updateParams.setContext(api.getContext());
        updateParams.setProvider(api.getProvider());
        updateParams.setRoles(api.getRoles());
        updateParams.setTags(api.getTags());
        updateParams.setTiers(api.getTiers());
        updateParams.setHttpChecked(api.isHttpChecked());
        updateParams.setHttpsChecked(api.isHttpsChecked());
        updateParams.setEndpointType(api.getEndpointType());
        updateParams.setEndpointUsername(api.getEndpointUsername());
        updateParams.setEndpointPassword(api.getEndpointPassword());
        updateParams.setVisibility(api.getVisibility());
        updateParams.setEndpointConfig(api.getEndpointConfig());
        updateParams.setThumb(api.getThumbnailImageUrl());
        return updateParams;
    }

}
