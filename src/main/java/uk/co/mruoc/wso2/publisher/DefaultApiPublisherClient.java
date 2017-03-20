package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;
import uk.co.mruoc.wso2.Credentials;
import uk.co.mruoc.wso2.LoginAction;
import uk.co.mruoc.wso2.LogoutAction;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.addapi.AddApiAction;
import uk.co.mruoc.wso2.publisher.addapi.AddApiParams;
import uk.co.mruoc.wso2.publisher.apiexists.ApiExistsAction;
import uk.co.mruoc.wso2.publisher.getapi.Api;
import uk.co.mruoc.wso2.publisher.getapi.GetApiAction;
import uk.co.mruoc.wso2.publisher.listallapis.ApiSummary;
import uk.co.mruoc.wso2.publisher.listallapis.ListAllApisAction;
import uk.co.mruoc.wso2.publisher.removeapi.RemoveApiAction;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusAction;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParams;
import uk.co.mruoc.wso2.publisher.updateapi.UpdateApiAction;
import uk.co.mruoc.wso2.publisher.updateapi.UpdateApiParams;

import java.util.List;

public class DefaultApiPublisherClient implements ApiPublisherClient {

    private final LoginAction loginAction;
    private final LogoutAction logoutAction;
    private final ListAllApisAction listAllApisAction;
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
        this(new LoginAction(client, new PublisherLoginUrlBuilder(hostUrl), new PublisherResponseErrorChecker()),
                new LogoutAction(client, new PublisherLogoutUrlBuilder(hostUrl), new PublisherResponseErrorChecker()),
                new ListAllApisAction(client, hostUrl),
                new GetApiAction(client, hostUrl),
                new AddApiAction(client, hostUrl),
                new ApiExistsAction(client, hostUrl),
                new UpdateApiAction(client, hostUrl),
                new RemoveApiAction(client, hostUrl),
                new SetStatusAction(client, hostUrl));
    }

    public DefaultApiPublisherClient(LoginAction loginAction,
                                     LogoutAction logoutAction,
                                     ListAllApisAction listAllApisAction,
                                     GetApiAction getAction,
                                     AddApiAction addAction,
                                     ApiExistsAction existsAction,
                                     UpdateApiAction updateAction,
                                     RemoveApiAction removeAction,
                                     SetStatusAction setStatusAction) {
        this.loginAction = loginAction;
        this.logoutAction = logoutAction;
        this.listAllApisAction = listAllApisAction;
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
        return listAllApisAction.listAllApis();
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
