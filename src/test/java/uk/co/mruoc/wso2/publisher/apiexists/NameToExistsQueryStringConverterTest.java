package uk.co.mruoc.wso2.publisher.apiexists;

import org.junit.Test;
import uk.co.mruoc.wso2.publisher.apiexists.NameToApiExistsQueryStringConverter;

import static org.assertj.core.api.Assertions.assertThat;

public class NameToExistsQueryStringConverterTest {

    private static final String NAME = "api-name";

    private final NameToApiExistsQueryStringConverter converter = new NameToApiExistsQueryStringConverter();

    @Test
    public void shouldBuildQueryString() {
        String expectedQueryString = "?action=isAPINameExist&apiName=" + NAME;

        String result = converter.convert(NAME);

        assertThat(result).isEqualTo(expectedQueryString);
    }

}
