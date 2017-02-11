package uk.co.mruoc.wso2;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSummaryDeserializerTest {

    private static final String RESPONSE_FILE_PATH = "/uk/co/mruoc/wso2/";

    private final FileLoader fileLoader = new FileLoader();
    private final ApiSummaryDeserializer deserializer = new ApiSummaryDeserializer();

    @Test
    public void shouldDeserializeJson() {
        JsonElement element = loadJson("colleague-api-summary.json");

        ApiSummary summary = deserializer.deserialize(element, null, null);

        assertThat(summary).isEqualToComparingFieldByField(new ColleagueApiSummary());
    }

    private JsonElement loadJson(String filename) {
        String path = buildPath(filename);
        String content = fileLoader.loadContent(path);
        JsonParser parser = new JsonParser();
        return parser.parse(content);
    }

    private String buildPath(String filename) {
        return RESPONSE_FILE_PATH + filename;
    }

}
