package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultAddApiParamsToQueryStringConverterTest {

    private DefaultAddApiParamsToQueryStringConverter converter = new DefaultAddApiParamsToQueryStringConverter();

    private AddApiParams params = mock(AddApiParams.class);

    @Test
    public void shouldConvertName() {
        given(params.getName()).willReturn("api-name");

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=addAPI&name=api-name");
    }

}
