package uk.co.mruoc.wso2.store;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.time.DateTimeZone.UTC;

public class StoreJsonParserTest {

    @Test
    public void shouldParseName() {
        StoreJsonParser parser = new StoreJsonParser("{ \"name\": \"application-name\"}");

        assertThat(parser.getName()).isEqualTo("application-name");
    }

    @Test
    public void shouldParseApplication() {
        StoreJsonParser parser = new StoreJsonParser("{ \"application\": \"application-name\"}");

        assertThat(parser.getApplication()).isEqualTo("application-name");
    }

    @Test
    public void shouldParseId() {
        StoreJsonParser parser = new StoreJsonParser("{ \"id\": 7}");

        assertThat(parser.getId()).isEqualTo(7);
    }

    @Test
    public void shouldParseApplicationId() {
        StoreJsonParser parser = new StoreJsonParser("{ \"applicationId\": 7}");

        assertThat(parser.getApplicationId()).isEqualTo(7);
    }

    @Test
    public void shouldParseValidityTime() {
        StoreJsonParser parser = new StoreJsonParser("{ \"validityTime\": 7}");

        assertThat(parser.getValidityTime()).isEqualTo(new DateTime(7L, UTC));
    }

    @Test
    public void shouldParseConsumerKey() {
        StoreJsonParser parser = new StoreJsonParser("{ \"consumerKey\": Consumer_Key}");

        assertThat(parser.getConsumerKey()).isEqualTo("Consumer_Key");
    }

    @Test
    public void shouldParseConsumerSecret() {
        StoreJsonParser parser = new StoreJsonParser("{ \"consumerSecret\": Consumer_Secret}");

        assertThat(parser.getConsumerSecret()).isEqualTo("Consumer_Secret");
    }

    @Test
    public void shouldParseAccessToken() {
        StoreJsonParser parser = new StoreJsonParser("{ \"accessToken\": Access_Token}");

        assertThat(parser.getAccessToken()).isEqualTo("Access_Token");
    }

    @Test
    public void shouldCreateException() {
        StoreJsonParser parser = new StoreJsonParser("{ }");

        assertThat(parser.createException("")).isInstanceOf(ApiStoreException.class);
    }

}
