package uk.co.mruoc.wso2;

import org.junit.*;
import org.testcontainers.containers.GenericContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiStoreClientIntegrationTest {

    private static final String DOCKER_IMAGE = "michaelruocco/wso2am:1.9.1";
    private static final int PORT = 9443;

    private final StartupCheckLogConsumer logConsumer = new Wso2StartupCheckLogConsumer();
    private final Credentials credentials = new TestCredentials();
    private final TestUrlBuilder urlBuilder = new TestUrlBuilder();

    @Rule
    public final GenericContainer container = new GenericContainer(DOCKER_IMAGE)
            .withExposedPorts(PORT)
            .withLogConsumer(logConsumer);

    private ApiStoreClient client;

    @Before
    public void setUp() {
        client = new DefaultApiStoreClient(urlBuilder.build(container, PORT));
        logConsumer.waitForStartupMessageInLog();
        client.login(credentials);
    }

    @After
    public void tearDown() {
        client.logout();
    }

    @Test
    @Ignore
    public void shouldAddApplication() {
        DefaultAddApplicationParams params = new FakeAddApplicationParams();

        assertThat(client.addApplication(params)).isTrue();
    }

    @Test
    @Ignore
    public void listAllShouldReturnDeployedApplications() {
        DefaultAddApplicationParams params = new FakeAddApplicationParams();
        client.addApplication(params);

        List<ApiApplication> applications = client.listAllApplications();

        ApiApplication application = applications.get(0);
        assertThat(applications.size()).isEqualTo(1);
        assertThat(application.getApplicationName()).isEqualTo(params.getApplicationName());
        assertThat(application.getApplicationId()).isGreaterThan(0);
    }

}
