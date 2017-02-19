package uk.co.mruoc.wso2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPublisherClientIntegrationTest {

    private static final int CONTAINER_START_TIMEOUT = 60000;
    private static final String BASE_URL = "https://localhost:9443";

    private final Wso2ContainerStartupChecker startupChecker = new Wso2ContainerStartupChecker(CONTAINER_START_TIMEOUT);

    @Rule
    public final GenericContainer container = new Wso2Container("michaelruocco/wso2am:1.9.1")
            .withExposedPorts(9443, 9763)
            .withLogConsumer(startupChecker);

    private final Credentials credentials = new Credentials("admin", "admin");
    private final ApiPublisherClient client = new DefaultApiPublisherClient(BASE_URL);

    @Before
    public void setUp() {
        startupChecker.waitForContainerToStart();
        client.login(credentials);
    }

    @Test
    public void listAllShouldReturnNoSummariesIfNoApisDeployed() {
        List<ApiSummary> summaries = client.listAll();

        assertThat(summaries).isEmpty();
    }

    @Test
    public void shouldAddApi() {
        AddApiParams params = StubAddApiParamsBuilder.build();

        assertThat(client.addApi(params)).isTrue();
    }

    @Test
    public void shouldReturnWhetherApiExists() {
        AddApiParams params = StubAddApiParamsBuilder.build();

        client.addApi(params);

        assertThat(client.exists(params.getName())).isTrue();
    }

    @Test
    public void shouldUpdateApi() {
        String updatedDescription = "updatedDescription";

        AddApiParams addParams = StubAddApiParamsBuilder.build();

        client.addApi(addParams);

        DefaultUpdateApiParams updateParams = new DefaultUpdateApiParams();
        updateParams.setDescription(updatedDescription);
        updateParams.setName(addParams.getName());
        updateParams.setDescription(updatedDescription);

        client.updateApi(updateParams);

        DefaultGetApiParams getParams = new DefaultGetApiParams();
        getParams.setName(addParams.getName());
        getParams.setProvider("admin");
        getParams.setVersion(addParams.getVersion());

        Api api = client.getApi(getParams);

        assertThat(api.getDescription()).isEqualTo(updatedDescription);
    }

    @After
    public void tearDown() {
        client.logout();
    }

}
