package uk.co.mruoc.wso2;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.util.List;

public class DefaultApiPublisherClient implements ApiPublisherClient {

    private final LoginAction loginAction;
    private final LogoutAction logoutAction;
    private final ListAllAction listAllAction;
    private final GetApiAction getAction;
    private final AddApiAction addAction;
    private final ApiExistsAction existsAction;
    private final UpdateApiAction updateAction;
    private final RemoveApiAction removeAction;
    private final SetStatusAction setStatusAction;

    public DefaultApiPublisherClient(String hostUrl) {
        this(new SimpleHttpClient(), hostUrl);
    }

    public DefaultApiPublisherClient(HttpClient client, String hostUrl) {
        this(new LoginAction(client, hostUrl),
                new LogoutAction(client, hostUrl),
                new ListAllAction(client, hostUrl),
                new GetApiAction(client, hostUrl),
                new AddApiAction(client, hostUrl),
                new ApiExistsAction(client, hostUrl),
                new UpdateApiAction(client, hostUrl),
                new RemoveApiAction(client, hostUrl),
                new SetStatusAction(client, hostUrl));
    }

    public DefaultApiPublisherClient(LoginAction loginAction,
                                     LogoutAction logoutAction,
                                     ListAllAction listAllAction,
                                     GetApiAction getAction,
                                     AddApiAction addAction,
                                     ApiExistsAction existsAction,
                                     UpdateApiAction updateAction,
                                     RemoveApiAction removeAction,
                                     SetStatusAction setStatusAction) {
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
        this.listAllAction = listAllAction;
        this.getAction = getAction;
        this.addAction = addAction;
        this.existsAction = existsAction;
        this.updateAction = updateAction;
        this.removeAction = removeAction;
        this.setStatusAction = setStatusAction;
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
        return getAction.getApi(params);
    }

    @Override
    public boolean addApi(AddApiParams params) {
        return addAction.addApi(params);
    }

    @Override
    public boolean apiExists(String name) {
        return existsAction.apiExists(name);
    }

    @Override
    public boolean updateApi(UpdateApiParams params) {
        return updateAction.updateApi(params);
    }

    @Override
    public boolean removeApi(SelectApiParams params) {
        return removeAction.removeApi(params);
    }

    @Override
    public boolean setStatus(SetStatusParams params) {
        return setStatusAction.setStatus(params);
    }

}
