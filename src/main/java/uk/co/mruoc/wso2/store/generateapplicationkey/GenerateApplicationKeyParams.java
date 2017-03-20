package uk.co.mruoc.wso2.store.generateapplicationkey;

import java.util.List;

public interface GenerateApplicationKeyParams {

    String getApplicationName();

    ApiKeyType getKeyType();

    String getCallbackUrl();

    List<String> getAuthorizedDomains();

    int getValidityTimeInSeconds();

}
