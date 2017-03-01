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
    public void listAllShouldReturnSummariesForDeployedApis() {
        AddApiParams params = StubAddApiParamsBuilder.build();
        client.addApi(params);

        List<ApiSummary> summaries = client.listAllApis();

        assertThat(summaries.size()).isEqualTo(1);
        assertThat(summaries.get(0)).isEqualToComparingFieldByField(new RestProductApiSummary());
    }

    @Test
    public void shouldReturnApiExists() {
        AddApiParams params = StubAddApiParamsBuilder.build();

        client.addApi(params);

        assertThat(client.apiExists(params.getApiName())).isTrue();
    }

    @Test
    public void shouldRemoveApi() {
        AddApiParams addApiParams = StubAddApiParamsBuilder.build();
        client.addApi(addApiParams);
        assertThat(client.apiExists(addApiParams.getApiName())).isTrue();

        SelectApiParams selectApiParams = toSelectParams(addApiParams);
        client.removeApi(selectApiParams);
        assertThat(client.apiExists(addApiParams.getApiName())).isFalse();
    }

    @Test
    public void shouldUpdateApi() {
        String updatedDescription = "updatedDescription";

        AddApiParams addParams = StubAddApiParamsBuilder.build();
        client.addApi(addParams);

        SelectApiParams selectApiParams = toSelectParams(addParams);
        Api api = client.getApi(selectApiParams);

        DefaultUpdateApiParams updateParams = new DefaultUpdateApiParams(api);
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
        selectParams.setName(addParams.getApiName());
        selectParams.setVersion(addParams.getApiVersion());
        selectParams.setProvider(USERNAME);
        return selectParams;
    }

}
