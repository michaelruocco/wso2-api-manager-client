package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameToExistsQueryStringConverterTest {

    private static final String NAME = "api-name";

    private final NameToExistsQueryStringConverter converter = new DefaultNameToExistsQueryStringConverter();

    @Test
    public void shouldBuildQueryString() {
        String expectedQueryString = "?action=isAPINameExist&apiName=" + NAME;

        String result = converter.convert(NAME);

        assertThat(result).isEqualTo(expectedQueryString);
    }

}
