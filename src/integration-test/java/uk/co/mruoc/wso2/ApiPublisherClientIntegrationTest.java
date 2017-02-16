package uk.co.mruoc.wso2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import uk.co.mruoc.wso2.DefaultAddApiParams.DefaultAddApiParamsBuilder;

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
        AddApiParams params = new DefaultAddApiParamsBuilder()
                .setName("rest-product")
                .setContext("/product")
                .setVersion("v1")
                .setDescription("Rest Product API")
                .setTags("prod", "rest-product", "product")
                .setSwagger("{\"consumes\":[\"application/json\"],\"info\":{\"description\":\"rest-taxonomy : (build 20170103134850)\",\"title\":\"rest-taxonomy\",\"version\":\"v1\"},\"paths\":{\"/z20170103134850\":{\"get\":{\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}},\"/{catalog}/paged*\":{\"get\":{\"parameters\":[{\"description\":\"Catalog id (for ex. GroupMasterProductCatalog)\",\"in\":\"path\",\"name\":\"catalog\",\"required\":true,\"type\":\"string\"},{\"in\":\"query\",\"name\":\"limit\",\"required\":true,\"type\":\"integer\"},{\"in\":\"query\",\"name\":\"offset\",\"required\":true,\"type\":\"integer\"}],\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}}},\"produces\":[\"application/json\"],\"schemes\":[\"https\"],\"swagger\":\"2.0\"}")
                .setEndpointConfig("{\"production_endpoints\": {\"url\":\"http://ws-uat1.dev.tppim.co.uk/pimwebservices/services/rest-taxonomy-entity-v1/taxonomy\", \"config\": null},\"endpoint_type\":\"http\"}")
                .build();

        assertThat(client.addApi(params)).isTrue();
    }

    @After
    public void tearDown() {
        client.logout();
    }

}
