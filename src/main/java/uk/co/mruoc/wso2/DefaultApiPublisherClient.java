package uk.co.mruoc.wso2;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.lang.reflect.Type;
import java.util.List;

public class DefaultApiPublisherClient implements ApiPublisherClient {

    private final HttpClient client;
    private final LoginUrlBuilder loginUrlBuilder;
    private final LogoutUrlBuilder logoutUrlBuilder;
    private final ListAllUrlBuilder listAllUrlBuilder;
    private final GetApiUrlBuilder getApiUrlBuilder;
    private final AddApiUrlBuilder addApiUrlBuilder;
    private final ApiExistsUrlBuilder apiExistsUrlBuilder;
    private final UpdateApiUrlBuilder updateApiUrlBuilder;
    private final Gson gson;

    public DefaultApiPublisherClient(String hostUrl) {
        this(new SimpleHttpClient(),
                new DefaultLoginUrlBuilder(hostUrl),
                new DefaultLogoutUrlBuilder(hostUrl),
                new DefaultListAllUrlBuilder(hostUrl),
                new DefaultGetApiUrlBuilder(hostUrl),
                new DefaultAddApiUrlBuilder(hostUrl),
                new DefaultApiExistsUrlBuilder(hostUrl),
                new DefaultUpdateApiUrlBuilder(hostUrl));
    }

    public DefaultApiPublisherClient(HttpClient client,
                                     LoginUrlBuilder loginUrlBuilder,
                                     LogoutUrlBuilder logoutUrlBuilder,
                                     ListAllUrlBuilder listAllUrlBuilder,
                                     GetApiUrlBuilder getApiUrlBuilder,
                                     AddApiUrlBuilder addApiUrlBuilder,
                                     ApiExistsUrlBuilder apiExistsUrlBuilder,
                                     UpdateApiUrlBuilder updateApiUrlBuilder) {
        this.client = client;
        this.loginUrlBuilder = loginUrlBuilder;
        this.logoutUrlBuilder = logoutUrlBuilder;
        this.listAllUrlBuilder = listAllUrlBuilder;
        this.getApiUrlBuilder = getApiUrlBuilder;
        this.addApiUrlBuilder = addApiUrlBuilder;
        this.apiExistsUrlBuilder = apiExistsUrlBuilder;
        this.updateApiUrlBuilder = updateApiUrlBuilder;
        this.gson = buildGson();
    }

    @Override
    public boolean login(Credentials credentials) {
        String url = loginUrlBuilder.build(credentials);
        Response response = client.post(url, "");
        checkForError(response);
        return true;
    }

    @Override
    public List<ApiSummary> listAll() {
        String url = listAllUrlBuilder.build();
        Response response = client.get(url);
        checkForError(response);
        return toApiSummaries(response);
    }

    @Override
    public boolean logout() {
        String url = logoutUrlBuilder.build();
        Response response = client.get(url);
        checkForError(response);
        return true;
    }

    @Override
    public Api getApi(GetApiParams params) {
        String url = getApiUrlBuilder.build(params);
        Response response = client.get(url);
        checkForError(response);
        return toApi(response);
    }

    @Override
    public boolean addApi(AddApiParams params) {
        String url = addApiUrlBuilder.build(params);
        Response response = client.post(url, "");
        checkForError(response);
        return true;
    }

    @Override
    public boolean exists(String name) {
        String url = apiExistsUrlBuilder.build(name);
        Response response = client.get(url);
        checkForError(response);
        return exists(response);
    }

    @Override
    public boolean updateApi(UpdateApiParams params) {
        String url = updateApiUrlBuilder.build(params);
        Response response = client.get(url);
        checkForError(response);
        return true;
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
        Type listType = new TypeToken<List<ApiSummary>>() {}.getType();
        return gson.fromJson(json.get("apis"), listType);
    }

    private Api toApi(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        return gson.fromJson(json.get("api"), Api.class);
    }

    private void checkForError(Response response) {
        if (hasError(response))
            throw new ApiPublisherException(buildErrorMessage(response));
    }

    private boolean hasError(Response response) {
        if (response.getStatusCode() != 200)
            return true;
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getError();
    }

    private boolean exists(Response response) {
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getExists();
    }

    private static String buildErrorMessage(Response response) {
        return "status code: " + response.getStatusCode() + " body: " + response.getBody();
    }

}
