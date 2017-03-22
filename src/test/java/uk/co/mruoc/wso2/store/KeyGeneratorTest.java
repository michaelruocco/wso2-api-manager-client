package uk.co.mruoc.wso2.store;

import org.junit.Test;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyParams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KeyGeneratorTest {

    private final ApiStoreClient storeClient = mock(ApiStoreClient.class);
    private final KeyGeneratorParams keyGeneratorParams = mock(KeyGeneratorParams.class);

    private final KeyGenerator keyGenerator = new KeyGenerator(storeClient);

    @Test
    public void shouldGenerateToken() {
        ApplicationKey expectedKey = givenKeyWillBeGenerated();

        ApplicationKey key = keyGenerator.generate(keyGeneratorParams);

        assertThat(key).isEqualTo(expectedKey);
        verify(storeClient).addApplication(keyGeneratorParams);
        verify(storeClient).addSubscription(keyGeneratorParams);
    }

    @Test
    public void cleanUpShouldRemoveSubscriptionAndApplication() {
        String applicationName = "application-name";
        given(keyGeneratorParams.getApplicationName()).willReturn(applicationName);
        given(storeClient.removeApplication(applicationName)).willReturn(true);

        boolean result = keyGenerator.cleanUp(keyGeneratorParams);

        assertThat(result).isTrue();
        verify(storeClient).removeSubscription(keyGeneratorParams);
        verify(storeClient).removeApplication(applicationName);
    }

    private ApplicationKey givenKeyWillBeGenerated() {
        ApplicationKey applicationKey = mock(ApplicationKey.class);
        given(storeClient.generateApplicationKey(any(GenerateApplicationKeyParams.class))).willReturn(applicationKey);
        return applicationKey;
    }

}
