package uk.co.mruoc.wso2.store.listallapplications;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;
import uk.co.mruoc.wso2.store.StoreResponseErrorChecker;

import java.lang.reflect.Type;
import java.util.List;

public class ListAllApplicationsAction {

    private final ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private final HttpClient client;
    private final ListAllApplicationsUrlBuilder urlBuilder;
    private final Gson gson = buildGson();

    public ListAllApplicationsAction(HttpClient client, String hostUrl) {
        this(client, new ListAllApplicationsUrlBuilder(hostUrl));
    }

    public ListAllApplicationsAction(HttpClient client, ListAllApplicationsUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public List<ApiApplication> listAllApplications() {
        String url = urlBuilder.build();
        Response response = client.get(url);
        errorChecker.checkForError(response);
        return toApiApplications(response);
    }

    private List<ApiApplication> toApiApplications(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        Type listType = new TypeToken<List<DefaultApiApplication>>() {}.getType();
        return gson.fromJson(json.get("applications"), listType);
    }

    private static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DefaultApiApplication.class, new ApiApplicationDeserializer());
        return gsonBuilder.create();
    }

}
