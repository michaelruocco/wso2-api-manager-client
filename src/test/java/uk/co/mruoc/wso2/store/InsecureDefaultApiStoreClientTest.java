package uk.co.mruoc.wso2.store;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InsecureDefaultApiStoreClientTest {

    private final InsecureDefaultApiStoreClient client = new InsecureDefaultApiStoreClient("");

    @Test
    public void shouldImplementApiStoreClient() {
        assertThat(client).isInstanceOf(ApiStoreClient.class);
    }

}
