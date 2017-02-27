package uk.co.mruoc.wso2;

import com.google.gson.*;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

public class GetApiAction {

    private final HttpClient client;
    private final GetApiUrlBuilder urlBuilder;
    private final Gson gson = buildGson();

    public GetApiAction(String hostUrl) {
        this(new SimpleHttpClient(), new DefaultGetApiUrlBuilder(hostUrl));
    }

    public GetApiAction(HttpClient client, GetApiUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public Api getApi(SelectApiParams params) {
        String url = urlBuilder.build(params);
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return toApi(response);
    }

    private Api toApi(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        return gson.fromJson(json.get("api"), DefaultApi.class);
    }

    private static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DefaultApi.class, new ApiDeserializer());
        return gsonBuilder.create();
    }

}
