package uk.co.mruoc.wso2;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

import java.lang.reflect.Type;
import java.util.List;

public class ListAllApisAction {

    private final ResponseErrorChecker errorChecker = new PublisherResponseErrorChecker();
    private final HttpClient client;
    private final ListAllApisUrlBuilder urlBuilder;
    private final Gson gson = buildGson();

    public ListAllApisAction(HttpClient client, String hostUrl) {
        this(client, new ListAllApisUrlBuilder(hostUrl));
    }

    public ListAllApisAction(HttpClient client, ListAllApisUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public List<ApiSummary> listAllApis() {
        String url = urlBuilder.build();
        Response response = client.get(url);
        errorChecker.checkForError(response);
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
