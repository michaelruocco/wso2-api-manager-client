package uk.co.mruoc.wso2.publisher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InsecureDefaultApiPublisherClientTest {

    private final InsecureDefaultApiPublisherClient client = new InsecureDefaultApiPublisherClient("");

    @Test
    public void shouldImplementApiPublisherClient() {
        assertThat(client).isInstanceOf(ApiPublisherClient.class);
    }

}
