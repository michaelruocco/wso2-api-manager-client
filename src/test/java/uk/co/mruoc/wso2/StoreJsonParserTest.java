package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreJsonParserTest {

    @Test
    public void shouldParseName() {
        StoreJsonParser parser = new StoreJsonParser("{ \"name\": \"application-name\"}");

        assertThat(parser.getName()).isEqualTo("application-name");
    }

    @Test
    public void shouldParseId() {
        StoreJsonParser parser = new StoreJsonParser("{ \"id\": 7}");

        assertThat(parser.getId()).isEqualTo(7);
    }

}
