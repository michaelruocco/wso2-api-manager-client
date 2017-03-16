package uk.co.mruoc.wso2.store;

import java.util.List;

public interface GenerateApplicationKeyParams {

    String getApplicationName();

    ApiKeyType getKeyType();

    String getCallbackUrl();

    List<String> getAuthorizedDomains();

    int getValidityTimeInSeconds();

}
