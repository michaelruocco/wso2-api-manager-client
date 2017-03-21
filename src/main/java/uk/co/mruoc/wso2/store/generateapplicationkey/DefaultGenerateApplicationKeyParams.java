package uk.co.mruoc.wso2.store.generateapplicationkey;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultGenerateApplicationKeyParams implements GenerateApplicationKeyParams {

    private String applicationName = EMPTY;
    private ApiKeyType keyType = ApiKeyType.PRODUCTION;
    private String callbackUrl = EMPTY;
    private List<String> authorizedDomains = Arrays.asList("ALL");
    private int validityTimeInSeconds = -1;

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public ApiKeyType getKeyType() {
        return keyType;
    }

    @Override
    public String getCallbackUrl() {
        return callbackUrl;
    }

    @Override
    public List<String> getAuthorizedDomains() {
        return authorizedDomains;
    }

    @Override
    public int getValidityTimeInSeconds() {
        return validityTimeInSeconds;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setKeyType(ApiKeyType keyType) {
        this.keyType = keyType;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public void setAuthorizedDomains(String... domains) {
        setAuthorizedDomains(Arrays.asList(domains));
    }

    public void setAuthorizedDomains(List<String> authorizedDomains) {
        this.authorizedDomains = authorizedDomains;
    }

    public void setValidityTimeInSeconds(int validityTimeInSeconds) {
        this.validityTimeInSeconds = validityTimeInSeconds;
    }

}
