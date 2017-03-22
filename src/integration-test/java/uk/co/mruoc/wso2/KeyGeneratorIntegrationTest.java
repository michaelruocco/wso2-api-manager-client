package uk.co.mruoc.wso2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import uk.co.mruoc.wso2.publisher.ApiPublisherClient;
import uk.co.mruoc.wso2.publisher.DefaultApiPublisherClient;
import uk.co.mruoc.wso2.publisher.DefaultSetStatusParams;
import uk.co.mruoc.wso2.publisher.StubAddApiParamsBuilder;
import uk.co.mruoc.wso2.publisher.addapi.AddApiParams;
import uk.co.mruoc.wso2.store.*;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.publisher.ApiStatus.PUBLISHED;

public class KeyGeneratorIntegrationTest {

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
    private KeyGenerator keyGenerator;

    @Before
    public void setUp() {
        logConsumer.waitForStartupMessageInLog(90000, 1000);

        publisherClient = new DefaultApiPublisherClient(urlBuilder.build(container, PORT));
        keyGenerator = new KeyGenerator(urlBuilder.build(container, PORT));

        publisherClient.login(credentials);
        keyGenerator.login(credentials);
    }

    @After
    public void tearDown() {
        publisherClient.logout();
        keyGenerator.logout();
    }

    @Test
    public void shouldGenerateKey() {
        AddApiParams addApiParams = givenApiHasBeenAdded();
        givenApiHasBeenPublished(addApiParams);

        DefaultKeyGeneratorParams params = new DefaultKeyGeneratorParams();
        params.setApiName(addApiParams.getApiName());
        params.setApiVersion(addApiParams.getApiVersion());
        params.setProvider(addApiParams.getProvider());
        params.setApplicationName("my-application");

        ApplicationKey key = keyGenerator.generate(params);

        assertThat(key.getConsumerKey()).isNotBlank();
        assertThat(key.getConsumerSecret()).isNotBlank();
        assertThat(key.getAccessToken()).isNotBlank();
        assertThat(key.getValidityTime().getMillis()).isPositive();
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

}
