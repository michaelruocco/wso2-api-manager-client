package uk.co.mruoc.wso2;

import com.google.gson.*;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultApiPublisherClient implements ApiPublisherClient {

    private final HttpClient client;
    private final LoginAction loginAction;
    private final LogoutAction logoutAction;
    private final ListAllAction listAllAction;
    private final GetApiUrlBuilder getApiUrlBuilder;
    private final AddApiUrlBuilder addApiUrlBuilder;
    private final ApiExistsUrlBuilder apiExistsUrlBuilder;
    private final UpdateApiUrlBuilder updateApiUrlBuilder;
    private final RemoveApiUrlBuilder removeApiUrlBuilder;
    private final Gson gson;

    public DefaultApiPublisherClient(String hostUrl) {
        this(new SimpleHttpClient(),
                new LoginAction(hostUrl),
                new LogoutAction(hostUrl),
                new ListAllAction(hostUrl),
                new DefaultGetApiUrlBuilder(hostUrl),
                new DefaultAddApiUrlBuilder(hostUrl),
                new DefaultApiExistsUrlBuilder(hostUrl),
                new DefaultUpdateApiUrlBuilder(hostUrl),
                new DefaultRemoveApiUrlBuilder(hostUrl));
    }

    public DefaultApiPublisherClient(HttpClient client,
                                     LoginAction loginAction,
                                     LogoutAction logoutAction,
                                     ListAllAction listAllAction,
                                     GetApiUrlBuilder getApiUrlBuilder,
                                     AddApiUrlBuilder addApiUrlBuilder,
                                     ApiExistsUrlBuilder apiExistsUrlBuilder,
                                     UpdateApiUrlBuilder updateApiUrlBuilder,
                                     RemoveApiUrlBuilder removeApiUrlBuilder) {
        this.client = client;
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
        this.listAllAction = listAllAction;
        this.getApiUrlBuilder = getApiUrlBuilder;
        this.addApiUrlBuilder = addApiUrlBuilder;
        this.apiExistsUrlBuilder = apiExistsUrlBuilder;
        this.updateApiUrlBuilder = updateApiUrlBuilder;
        this.removeApiUrlBuilder = removeApiUrlBuilder;
        this.gson = buildGson();
    }

    @Override
    public boolean login(Credentials credentials) {
        return loginAction.login(credentials);
    }

    @Override
    public List<ApiSummary> listAllApis() {
        return listAllAction.listAllApis();
    }

    @Override
    public boolean logout() {
        return logoutAction.logout();
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
