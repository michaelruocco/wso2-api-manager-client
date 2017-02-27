package uk.co.mruoc.wso2;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.lang.reflect.Type;
import java.util.List;

public class ListAllAction {

    private HttpClient client;
    private ListAllUrlBuilder urlBuilder;
    private Gson gson = buildGson();

    public ListAllAction(String hostUrl) {
        this(new SimpleHttpClient(), new DefaultListAllUrlBuilder(hostUrl));
    }

    public ListAllAction(HttpClient client, ListAllUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public List<ApiSummary> listAllApis() {
        String url = urlBuilder.build();
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return toApiSummaries(response);
    }

    private List<ApiSummary> toApiSummaries(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        Type listType = new TypeToken<List<DefaultApiSummary>>() {}.getType();
        return gson.fromJson(json.get("apis"), listType);
    }

    private static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DefaultApiSummary.class, new ApiSummaryDeserializer());
        return gsonBuilder.create();
    }

}
