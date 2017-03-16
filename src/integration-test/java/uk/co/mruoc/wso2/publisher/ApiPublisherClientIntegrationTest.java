package uk.co.mruoc.wso2.publisher;

import org.junit.*;
import org.testcontainers.containers.GenericContainer;
import uk.co.mruoc.wso2.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPublisherClientIntegrationTest {

    private static final String DOCKER_IMAGE = "michaelruocco/wso2am:1.9.1";
    private static final int PORT = 9443;

    private final StartupCheckLogConsumer logConsumer = new Wso2StartupCheckLogConsumer();
    private final Credentials credentials = new TestCredentials();
    private final TestUrlBuilder urlBuilder = new TestUrlBuilder();

    @Rule
    public final GenericContainer container = new GenericContainer(DOCKER_IMAGE)
            .withExposedPorts(PORT)
            .withLogConsumer(logConsumer);

    private ApiPublisherClient client;

    @Before
    public void setUp() {
        client = new DefaultApiPublisherClient(urlBuilder.build(container, PORT));
        logConsumer.waitForStartupMessageInLog();
        client.login(credentials);
    }

    @After
    public void tearDown() {
        client.logout();
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

        ApiToUpdateApiParamsConverter converter = new ApiToUpdateApiParamsConverter();
        DefaultUpdateApiParams updateParams = converter.convert(api);
        updateParams.setDescription(updatedDescription);
        updateParams.setSwagger(addParams.getSwagger());
        client.updateApi(updateParams);

        Api updatedApi = client.getApi(selectApiParams);

        assertThat(updatedApi.getDescription()).isEqualTo(updatedDescription);
    }

    @Test
    public void shouldSetApiStatus() {
        ApiStatus updatedStatus = ApiStatus.PUBLISHED;

        AddApiParams addParams = StubAddApiParamsBuilder.build();
        client.addApi(addParams);

        SelectApiParams selectApiParams = toSelectParams(addParams);
        Api api = client.getApi(selectApiParams);

        ApiToSetStatusParamsConverter converter = new ApiToSetStatusParamsConverter();
        DefaultSetStatusParams setStatusParams = converter.convert(api);
        setStatusParams.setStatus(updatedStatus);
        client.setStatus(setStatusParams);

        Api updatedApi = client.getApi(selectApiParams);

        assertThat(updatedApi.getStatus()).isEqualTo(updatedStatus);
    }

    private SelectApiParams toSelectParams(AddApiParams addParams) {
        DefaultSelectApiParams selectParams = new DefaultSelectApiParams();
        selectParams.setName(addParams.getApiName());
        selectParams.setVersion(addParams.getApiVersion());
        selectParams.setProvider(addParams.getProvider());
        return selectParams;
    }

}
