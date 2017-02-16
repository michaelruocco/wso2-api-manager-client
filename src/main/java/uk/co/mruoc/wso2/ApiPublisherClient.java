package uk.co.mruoc.wso2;

import java.util.List;

public interface ApiPublisherClient {

    boolean login(Credentials credentials);

    boolean logout();

    List<ApiSummary> listAll();

    Api getApi(GetApiParams params);

    boolean addApi(AddApiParams params);
    
}
