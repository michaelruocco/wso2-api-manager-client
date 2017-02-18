package uk.co.mruoc.wso2;

import org.joda.time.DateTime;
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
    public void shouldParseProvider() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"provider\": \"admin\"}");

        assertThat(parser.getProvider()).isEqualTo("admin");
    }

    @Test
    public void shouldParseStatus() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"status\": \"PUBLISHED\"}");

        assertThat(parser.getStatus()).isEqualTo("PUBLISHED");
    }

    @Test
    public void shouldParseThumbnailImagePath() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"thumb\": \"path/thumb.png\"}");

        assertThat(parser.getThumbnailImagePath()).isEqualTo("path/thumb.png");
    }

    @Test
    public void shouldParseContext() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"context\": \"product/v1\"}");

        assertThat(parser.getContext()).isEqualTo("product/v1");
    }

    @Test
    public void shouldParseLastUpdated() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"lastUpdated\": \"1486464366000\"}");

        assertThat(parser.getLastUpdated()).isEqualTo(new DateTime(1486464366000l));
    }

    @Test
    public void shouldParseSubscriberCount() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"subs\": 3}");

        assertThat(parser.getSubscriberCount()).isEqualTo(3);
    }

    @Test
    public void shouldParseError() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"error\": true}");

        assertThat(parser.getError()).isTrue();
    }

    @Test
    public void shouldParseExists() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"exist\": true}");

        assertThat(parser.getExists()).isTrue();
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
    public void shouldThrowExceptionForNullTimeValue() {
        PublisherJsonParser parser = new PublisherJsonParser("{ \"lastUpdated\": null}");

        parser.getLastUpdated();
    }

}
