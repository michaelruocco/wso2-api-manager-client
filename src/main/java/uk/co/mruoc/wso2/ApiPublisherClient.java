package uk.co.mruoc.wso2;

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

}
