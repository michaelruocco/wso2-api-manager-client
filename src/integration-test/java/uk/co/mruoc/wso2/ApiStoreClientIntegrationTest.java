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
    public void shouldAddApplication() {
        DefaultAddApplicationParams params = new FakeAddApplicationParams();

        assertThat(client.addApplication(params)).isTrue();
    }

    @Test
    public void listAllShouldReturnDeployedApplications() {
        DefaultAddApplicationParams params = new FakeAddApplicationParams();
        client.addApplication(params);

        List<ApiApplication> applications = client.listAllApplications();

        assertThat(applications.size()).isEqualTo(2);
        assertThat(applications.get(0)).isEqualToComparingFieldByField(new DefaultApplication());
        assertThat(applications.get(1)).isEqualToComparingFieldByField(new TestApplication());
    }

    @Test
    @Ignore
    public void shouldRemoveApplication() {
        DefaultAddApplicationParams params = new FakeAddApplicationParams();
        client.addApplication(params);

        assertThat(client.removeApplication(params.getApplicationName())).isTrue();
    }

}
