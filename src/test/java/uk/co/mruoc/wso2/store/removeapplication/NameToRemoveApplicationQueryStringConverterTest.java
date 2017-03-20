package uk.co.mruoc.wso2.store.removeapplication;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameToRemoveApplicationQueryStringConverterTest {

    private static final String NAME = "application-name";

    private final NameToRemoveApplicationQueryStringConverter converter = new NameToRemoveApplicationQueryStringConverter();

    @Test
    public void shouldBuildQueryString() {
        String expectedQueryString = "?action=removeApplication&application=" + NAME;

        String result = converter.convert(NAME);

        assertThat(result).isEqualTo(expectedQueryString);
    }

}
