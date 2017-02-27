package uk.co.mruoc.wso2;

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
    private final GetApiAction getApiAction;
    private final AddApiUrlBuilder addApiUrlBuilder;
    private final ApiExistsUrlBuilder apiExistsUrlBuilder;
    private final UpdateApiUrlBuilder updateApiUrlBuilder;
    private final RemoveApiUrlBuilder removeApiUrlBuilder;

    public DefaultApiPublisherClient(String hostUrl) {
        this(new SimpleHttpClient(),
                new LoginAction(hostUrl),
                new LogoutAction(hostUrl),
                new ListAllAction(hostUrl),
                new GetApiAction(hostUrl),
                new DefaultAddApiUrlBuilder(hostUrl),
                new DefaultApiExistsUrlBuilder(hostUrl),
                new DefaultUpdateApiUrlBuilder(hostUrl),
                new DefaultRemoveApiUrlBuilder(hostUrl));
    }

    public DefaultApiPublisherClient(HttpClient client,
                                     LoginAction loginAction,
                                     LogoutAction logoutAction,
                                     ListAllAction listAllAction,
                                     GetApiAction getApiAction,
                                     AddApiUrlBuilder addApiUrlBuilder,
                                     ApiExistsUrlBuilder apiExistsUrlBuilder,
                                     UpdateApiUrlBuilder updateApiUrlBuilder,
                                     RemoveApiUrlBuilder removeApiUrlBuilder) {
        this.client = client;
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
        this.listAllAction = listAllAction;
        this.getApiAction = getApiAction;
        this.addApiUrlBuilder = addApiUrlBuilder;
        this.apiExistsUrlBuilder = apiExistsUrlBuilder;
        this.updateApiUrlBuilder = updateApiUrlBuilder;
        this.removeApiUrlBuilder = removeApiUrlBuilder;
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
        return getApiAction.getApi(params);
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

    private boolean exists(Response response) {
        PublisherJsonParser parser = new PublisherJsonParser(response.getBody());
        return parser.getExists();
    }

}
