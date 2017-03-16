package uk.co.mruoc.wso2.publisher;

import com.google.gson.JsonElement;
import org.junit.Test;
import uk.co.mruoc.wso2.JsonLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSummaryDeserializerTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";

    private final JsonLoader jsonLoader = new JsonLoader();
    private final ApiSummaryDeserializer deserializer = new ApiSummaryDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = loadJson("colleague-api-summary.json");

        ApiSummary summary = deserializer.deserialize(element, null, null);

        assertThat(summary).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

    private JsonElement loadJson(String filename) {
        String path = buildPath(filename);
        return jsonLoader.load(path);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
