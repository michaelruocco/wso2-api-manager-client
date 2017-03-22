package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.Credentials;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApplicationKey;

public class KeyGenerator {

    private final ApiStoreClient client;

    public KeyGenerator(String hostUrl) {
        this(new DefaultApiStoreClient(hostUrl));
    }

    public KeyGenerator(ApiStoreClient client) {
        this.client = client;
    }

    public boolean login(Credentials credentials) {
        return client.login(credentials);
    }

    public ApplicationKey generate(KeyGeneratorParams params) {
        client.addApplication(params);
        client.addSubscription(params);
        return client.generateApplicationKey(params);
    }

    public boolean cleanUp(KeyGeneratorParams params) {
        client.removeSubscription(params);
        return client.removeApplication(params.getApplicationName());
    }

    public boolean logout() {
        return client.logout();
    }

}
