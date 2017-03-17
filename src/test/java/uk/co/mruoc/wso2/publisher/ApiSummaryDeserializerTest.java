package uk.co.mruoc.wso2.publisher;

import com.google.gson.JsonElement;
import org.junit.Test;
import uk.co.mruoc.wso2.ResponseLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSummaryDeserializerTest {

    private final ResponseLoader responseLoader = new PublisherResponseLoader();
    private final ApiSummaryDeserializer deserializer = new ApiSummaryDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = responseLoader.loadJson("colleague-api-summary.json");

        ApiSummary summary = deserializer.deserialize(element, null, null);

        assertThat(summary).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

}
