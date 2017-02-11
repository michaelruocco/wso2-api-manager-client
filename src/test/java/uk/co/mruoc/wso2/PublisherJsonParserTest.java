package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherJsonParserTest {

    @Test
    public void shouldParseName() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"name\": \"api-name\"}");

        assertThat(parser.getName()).isEqualTo("api-name");
    }

    @Test
    public void shouldParseVersion() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"version\": \"v1\"}");

        assertThat(parser.getVersion()).isEqualTo("v1");
    }

    @Test
    public void shouldParseDescription() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"description\": \"A test api\"}");

        assertThat(parser.getDescription()).isEqualTo("A test api");
    }

    @Test
    public void shouldDefaultNullStringValueToEmptyString() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"name\": null}");

        assertThat(parser.getName()).isEqualTo("");
    }

    @Test
    public void shouldDefaultNullIntegerValueToZero() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"subs\": null}");

        assertThat(parser.getSubscriberCount()).isEqualTo(0);
    }

    @Test
    public void shouldDefaultNullBooleanValueToFalse() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"error\": null}");

        assertThat(parser.getError()).isFalse();
    }

    @Test(expected = ApiPublisherException.class)
    public void shouldThrowExceptionForNullTimeVaue() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"lastUpdated\": null}");

        parser.getLastUpdated();
    }

}
