package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.Credentials;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.addapi.AddApiParams;
import uk.co.mruoc.wso2.publisher.getapi.Api;
import uk.co.mruoc.wso2.publisher.listallapis.ApiSummary;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParams;
import uk.co.mruoc.wso2.publisher.updateapi.UpdateApiParams;

import java.util.List;

public interface ApiPublisherClient {

    boolean login(Credentials credentials);

    boolean logout();

    List<ApiSummary> listAllApis();

    Api getApi(SelectApiParams params);

    boolean addApi(AddApiParams params);

    boolean apiExists(String name);

    boolean updateApi(UpdateApiParams params);

    boolean removeApi(SelectApiParams params);

    boolean setStatus(SetStatusParams params);

}
