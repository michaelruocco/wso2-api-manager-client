package uk.co.mruoc.wso2;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.lang.reflect.Type;
import java.util.List;

public class DefaultApiPublisherClient implements ApiPublisherClient {

    private static final Logger LOG = LogManager.getLogger(DefaultApiPublisherClient.class);

    private final HttpClient client;
    private final AuthenticationUrlBuilder authenticationUrlBuilder;
    private final ListAllUrlBuilder listAllUrlBuilder;
    private final GetApiUrlBuilder getApiUrlBuilder;
    private final Gson gson;

    public DefaultApiPublisherClient(String hostUrl) {
        this(hostUrl, new SimpleHttpClient());
    }

    public DefaultApiPublisherClient(String hostUrl, HttpClient client) {
        this.authenticationUrlBuilder = new AuthenticationUrlBuilder(hostUrl);
        this.listAllUrlBuilder = new ListAllUrlBuilder(hostUrl);
        this.getApiUrlBuilder = new GetApiUrlBuilder(hostUrl);
        this.client = client;
        this.gson = buildGson();
    }

    @Override
    public boolean login(Credentials credentials) {
        String url = authenticationUrlBuilder.buildLoginUrl(credentials);
        Response response = client.post(url, "");
        log(response);
        checkForError(response);
        return true;
    }

    @Override
    public List<ApiSummary> listAll() {
        String url = listAllUrlBuilder.build();
        Response response = client.get(url);
        log(response);
        checkForError(response);
        return toApiSummaries(response);
    }

    @Override
    public boolean logout() {
        String url = authenticationUrlBuilder.buildLogoutUrl();
        Response response = client.get(url);
        log(response);
        checkForError(response);
        return true;
    }

    @Override
    public Api getApi(GetApiParams params) {
        String url = getApiUrlBuilder.build(params);
        Response response = client.get(url);
        log(response);
        checkForError(response);
        return toApi(response);
    }

    private Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ApiSummary.class, new ApiSummaryDeserializer());
        gsonBuilder.registerTypeAdapter(Api.class, new ApiDeserializer());
        return gsonBuilder.create();
    }

    private List<ApiSummary> toApiSummaries(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        Type listType = new TypeToken<List<ApiSummary>>(){}.getType();
        return gson.fromJson(json.get("apis"), listType);
    }

    private Api toApi(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        return gson.fromJson(json.get("api"), Api.class);
    }

    private void log(Response response) {
        LOG.info("status code " + response.getStatusCode());
        LOG.info("body " + response.getBody());
    }

    private void checkForError(Response response) {
        if (hasError(response))
            throw new ApiPublisherException(response);
    }

    private boolean hasError(Response response) {
        if (response.getStatusCode() != 200)
            return true;
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getError();
    }

}
