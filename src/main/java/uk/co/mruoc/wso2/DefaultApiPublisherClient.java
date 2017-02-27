package uk.co.mruoc.wso2;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.lang.reflect.Type;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultApiPublisherClient implements ApiPublisherClient {

    private final HttpClient client;
    private final LoginUrlBuilder loginUrlBuilder;
    private final LogoutUrlBuilder logoutUrlBuilder;
    private final ListAllUrlBuilder listAllUrlBuilder;
    private final GetApiUrlBuilder getApiUrlBuilder;
    private final AddApiUrlBuilder addApiUrlBuilder;
    private final ApiExistsUrlBuilder apiExistsUrlBuilder;
    private final UpdateApiUrlBuilder updateApiUrlBuilder;
    private final RemoveApiUrlBuilder removeApiUrlBuilder;
    private final Gson gson;

    public DefaultApiPublisherClient(String hostUrl) {
        this(new SimpleHttpClient(),
                new DefaultLoginUrlBuilder(hostUrl),
                new DefaultLogoutUrlBuilder(hostUrl),
                new DefaultListAllUrlBuilder(hostUrl),
                new DefaultGetApiUrlBuilder(hostUrl),
                new DefaultAddApiUrlBuilder(hostUrl),
                new DefaultApiExistsUrlBuilder(hostUrl),
                new DefaultUpdateApiUrlBuilder(hostUrl),
                new DefaultRemoveApiUrlBuilder(hostUrl));
    }

    public DefaultApiPublisherClient(HttpClient client,
                                     LoginUrlBuilder loginUrlBuilder,
                                     LogoutUrlBuilder logoutUrlBuilder,
                                     ListAllUrlBuilder listAllUrlBuilder,
                                     GetApiUrlBuilder getApiUrlBuilder,
                                     AddApiUrlBuilder addApiUrlBuilder,
                                     ApiExistsUrlBuilder apiExistsUrlBuilder,
                                     UpdateApiUrlBuilder updateApiUrlBuilder,
                                     RemoveApiUrlBuilder removeApiUrlBuilder) {
        this.client = client;
        this.loginUrlBuilder = loginUrlBuilder;
        this.logoutUrlBuilder = logoutUrlBuilder;
        this.listAllUrlBuilder = listAllUrlBuilder;
        this.getApiUrlBuilder = getApiUrlBuilder;
        this.addApiUrlBuilder = addApiUrlBuilder;
        this.apiExistsUrlBuilder = apiExistsUrlBuilder;
        this.updateApiUrlBuilder = updateApiUrlBuilder;
        this.removeApiUrlBuilder = removeApiUrlBuilder;
        this.gson = buildGson();
    }

    @Override
    public boolean login(Credentials credentials) {
        String url = loginUrlBuilder.build(credentials);
        Response response = client.post(url, EMPTY);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

    @Override
    public List<ApiSummary> listAllApis() {
        String url = listAllUrlBuilder.build();
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return toApiSummaries(response);
    }

    @Override
    public boolean logout() {
        String url = logoutUrlBuilder.build();
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

    @Override
    public Api getApi(SelectApiParams params) {
        String url = getApiUrlBuilder.build(params);
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return toApi(response);
    }

    @Override
    public boolean addApi(AddApiParams params) {
        String url = addApiUrlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

    @Override
    public boolean apiExists(String name) {
        String url = apiExistsUrlBuilder.build(name);
        Response response = client.get(url);
        ResponseErrorChecker.checkForError(response);
        return exists(response);
    }

    @Override
    public boolean updateApi(UpdateApiParams params) {
        String url = updateApiUrlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

    @Override
    public boolean removeApi(SelectApiParams params) {
        String url = removeApiUrlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        ResponseErrorChecker.checkForError(response);
        return true;
    }

    private Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DefaultApiSummary.class, new ApiSummaryDeserializer());
        gsonBuilder.registerTypeAdapter(DefaultApi.class, new ApiDeserializer());
        return gsonBuilder.create();
    }

    private List<ApiSummary> toApiSummaries(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        Type listType = new TypeToken<List<DefaultApiSummary>>() {}.getType();
        return gson.fromJson(json.get("apis"), listType);
    }

    private Api toApi(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        return gson.fromJson(json.get("api"), DefaultApi.class);
    }

    private boolean exists(Response response) {
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getExists();
    }

}
