package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.wso2.ApiTierAvailability.*;

public class ApiTierAvailabilityTest {

    @Test
    public void shouldConvertListOfNamesToListOfTiers() {
        List<String> inputs = Arrays.asList("gold", "SILVER ");

        List<ApiTierAvailability> result = toTiersList(inputs);

        assertThat(result).containsExactly(GOLD, SILVER);
    }

}
