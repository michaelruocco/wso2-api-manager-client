package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiKeyType.*;

public class ApiKeyTypeTest {

    @Test
    public void shouldConvertListOfNamesToListOfKeyTypes() {
        List<String> inputs = Arrays.asList("production", "SANDBOX ");

        List<ApiKeyType> result = toKeyTypeList(inputs);

        assertThat(result).containsExactly(PRODUCTION, SANDBOX);
    }

}
