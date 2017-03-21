package uk.co.mruoc.wso2.store.generateapplicationkey;

import com.google.gson.*;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;
import uk.co.mruoc.wso2.store.StoreResponseErrorChecker;

public class GenerateApplicationKeyAction {

    private final ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private HttpClient client;
    private GenerateApplicationKeyUrlBuilder urlBuilder;
    private final Gson gson = buildGson();

    public GenerateApplicationKeyAction(HttpClient client, String hostUrl) {
        this(client, new GenerateApplicationKeyUrlBuilder(hostUrl));
    }

    public GenerateApplicationKeyAction(HttpClient client, GenerateApplicationKeyUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public ApplicationKey generateKey(GenerateApplicationKeyParams params) {
        String url = urlBuilder.build(params);
        Response response = client.get(url);
        errorChecker.checkForError(response);
        return toApplicationKey(response);
    }

    private ApplicationKey toApplicationKey(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        JsonObject data = json.get("data").getAsJsonObject();
        return gson.fromJson(data.get("key"), ApplicationKey.class);
    }

    private static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ApplicationKey.class, new ApplicationKeyDeserializer());
        return gsonBuilder.create();
    }

}
