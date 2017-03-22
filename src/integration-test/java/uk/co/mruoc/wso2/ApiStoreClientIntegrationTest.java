package uk.co.mruoc.wso2;

import org.junit.*;
import org.testcontainers.containers.GenericContainer;
import uk.co.mruoc.wso2.publisher.*;
import uk.co.mruoc.wso2.publisher.addapi.AddApiParams;
import uk.co.mruoc.wso2.store.*;
import uk.co.mruoc.wso2.store.addapplication.AddApplicationParams;
import uk.co.mruoc.wso2.store.addapplication.DefaultAddApplicationParams;
import uk.co.mruoc.wso2.store.addsubscription.DefaultAddSubscriptionParams;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;
import uk.co.mruoc.wso2.store.generateapplicationkey.DefaultGenerateApplicationKeyParams;
import uk.co.mruoc.wso2.store.getsubscription.ApiSubscription;
import uk.co.mruoc.wso2.store.listallapplications.ApiApplication;
import uk.co.mruoc.wso2.store.listallapplications.DefaultApplication;
import uk.co.mruoc.wso2.store.listallapplications.TestApplication;
import uk.co.mruoc.wso2.store.removesubscription.DefaultRemoveSubscriptionParams;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.publisher.ApiStatus.*;

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

    private ApiPublisherClient publisherClient;
    private ApiStoreClient storeClient;

    @Before
    public void setUp() {
        logConsumer.waitForStartupMessageInLog(90000, 1000);

        publisherClient = new DefaultApiPublisherClient(urlBuilder.build(container, PORT));
        storeClient = new DefaultApiStoreClient(urlBuilder.build(container, PORT));

        publisherClient.login(credentials);
        storeClient.login(credentials);
    }

    @After
    public void tearDown() {
        publisherClient.logout();
        storeClient.logout();
    }

    @Test
    public void shouldAddApplication() {
        DefaultAddApplicationParams params = new FakeAddApplicationParams();

        assertThat(storeClient.addApplication(params)).isTrue();
    }

    @Test
    public void listAllShouldReturnDeployedApplications() {
        givenApplicationHasBeenAdded();

        List<ApiApplication> applications = storeClient.listAllApplications();

        assertThat(applications.size()).isEqualTo(2);
        assertThat(applications.get(0)).isEqualToComparingFieldByField(new DefaultApplication());
        assertThat(applications.get(1)).isEqualToComparingFieldByField(new TestApplication());
    }

    @Test
    public void shouldRemoveApplication() {
        AddApplicationParams params = givenApplicationHasBeenAdded();

        assertThat(storeClient.removeApplication(params.getApplicationName())).isTrue();
    }

    @Test
    public void shouldAddSubscription() {
        AddApiParams addApiParams = givenApiHasBeenAdded();
        givenApiHasBeenPublished(addApiParams);
        AddApplicationParams addApplicationParams = givenApplicationHasBeenAdded();
        DefaultAddSubscriptionParams addSubscriptionParams = toAddSubscriptionParams(addApiParams, addApplicationParams);

        assertThat(storeClient.addSubscription(addSubscriptionParams)).isTrue();
    }

    @Test
    public void shouldRemoveSubscription() {
        AddApiParams addApiParams = givenApiHasBeenAdded();
        givenApiHasBeenPublished(addApiParams);
        AddApplicationParams addApplicationParams = givenApplicationHasBeenAdded();
        DefaultRemoveSubscriptionParams removeSubscriptionParams = toRemoveSubscriptionParams(addApiParams, addApplicationParams);

        assertThat(storeClient.removeSubscription(removeSubscriptionParams)).isTrue();
    }

    @Test
    public void shouldListSubscriptions() {
        AddApiParams addApiParams = givenApiHasBeenAdded();
        givenApiHasBeenPublished(addApiParams);
        AddApplicationParams addApplicationParams = givenApplicationHasBeenAdded();
        DefaultAddSubscriptionParams addSubscriptionParams = toAddSubscriptionParams(addApiParams, addApplicationParams);

        storeClient.addSubscription(addSubscriptionParams);

        List<ApiSubscription> subscriptions = storeClient.getSubscriptionsByApi(addApiParams);
        assertThat(subscriptions.size()).isEqualTo(1);
        assertThat(subscriptions.get(0).getApplicationName()).isEqualTo(addApplicationParams.getApplicationName());
    }

    @Test
    public void shouldGenerateApplicationKey() {
        AddApiParams addApiParams = givenApiHasBeenAdded();
        givenApiHasBeenPublished(addApiParams);
        AddApplicationParams addApplicationParams = givenApplicationHasBeenAdded();
        DefaultAddSubscriptionParams addSubscriptionParams = toAddSubscriptionParams(addApiParams, addApplicationParams);
        givenApplicationHasBeenSubscribedToApi(addSubscriptionParams);

        DefaultGenerateApplicationKeyParams generateApplicationKeyParams = toGenerateApplicationKeyParams(addApplicationParams);
        ApplicationKey key = storeClient.generateApplicationKey(generateApplicationKeyParams);

        assertThat(key.getConsumerKey()).isNotBlank();
        assertThat(key.getConsumerSecret()).isNotBlank();
        assertThat(key.getAccessToken()).isNotBlank();
        assertThat(key.getValidityTime().getMillis()).isPositive();
    }

    private void givenApplicationHasBeenSubscribedToApi(DefaultAddSubscriptionParams addSubscriptionParams) {
        storeClient.addSubscription(addSubscriptionParams);
    }

    private AddApplicationParams givenApplicationHasBeenAdded() {
        AddApplicationParams params = new FakeAddApplicationParams();
        storeClient.addApplication(params);
        return params;
    }

    private AddApiParams givenApiHasBeenAdded() {
        AddApiParams addApiParams = StubAddApiParamsBuilder.build();
        publisherClient.addApi(addApiParams);
        return addApiParams;
    }

    private void givenApiHasBeenPublished(AddApiParams addApiParams) {
        DefaultSetStatusParams setStatusParams = toSetStatusParams(addApiParams);
        setStatusParams.setStatus(PUBLISHED);
        publisherClient.setStatus(setStatusParams);
    }

    private DefaultSetStatusParams toSetStatusParams(AddApiParams addApiParams) {
        DefaultSetStatusParams setStatusParams = new DefaultSetStatusParams();
        setStatusParams.setApiName(addApiParams.getApiName());
        setStatusParams.setApiVersion(addApiParams.getApiVersion());
        setStatusParams.setProvider(addApiParams.getProvider());
        return setStatusParams;
    }

    private DefaultAddSubscriptionParams toAddSubscriptionParams(AddApiParams addApiParams, AddApplicationParams addApplicationParams) {
        DefaultAddSubscriptionParams addSubscriptionParams = new DefaultAddSubscriptionParams();
        addSubscriptionParams.setApplicationName(addApplicationParams.getApplicationName());
        addSubscriptionParams.setApiName(addApiParams.getApiName());
        addSubscriptionParams.setApiVersion(addApiParams.getApiVersion());
        addSubscriptionParams.setProvider(addApiParams.getProvider());
        return addSubscriptionParams;
    }

    private DefaultRemoveSubscriptionParams toRemoveSubscriptionParams(AddApiParams addApiParams, AddApplicationParams addApplicationParams) {
        DefaultRemoveSubscriptionParams removeSubscriptionParams = new DefaultRemoveSubscriptionParams();
        removeSubscriptionParams.setApplicationName(addApplicationParams.getApplicationName());
        removeSubscriptionParams.setApiName(addApiParams.getApiName());
        removeSubscriptionParams.setApiVersion(addApiParams.getApiVersion());
        removeSubscriptionParams.setProvider(addApiParams.getProvider());
        return removeSubscriptionParams;
    }

    private DefaultGenerateApplicationKeyParams toGenerateApplicationKeyParams(AddApplicationParams addApplicationParams) {
        DefaultGenerateApplicationKeyParams generateApplicationKeyParams = new DefaultGenerateApplicationKeyParams();
        generateApplicationKeyParams.setApplicationName(addApplicationParams.getApplicationName());
        return generateApplicationKeyParams;
    }

}
