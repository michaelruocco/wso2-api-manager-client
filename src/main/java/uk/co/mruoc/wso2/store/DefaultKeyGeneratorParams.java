package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.store.addapplication.DefaultAddApplicationParams;
import uk.co.mruoc.wso2.store.addsubscription.DefaultAddSubscriptionParams;
import uk.co.mruoc.wso2.store.generateapplicationkey.ApiKeyType;
import uk.co.mruoc.wso2.store.generateapplicationkey.DefaultGenerateApplicationKeyParams;

import java.util.Arrays;
import java.util.List;

public class DefaultKeyGeneratorParams implements KeyGeneratorParams {

    private final DefaultGenerateApplicationKeyParams generateApplicationKeyParams = new DefaultGenerateApplicationKeyParams();
    private final DefaultAddApplicationParams addApplicationParams = new DefaultAddApplicationParams();
    private final DefaultAddSubscriptionParams addSubscriptionParams = new DefaultAddSubscriptionParams();

    @Override
    public String getApiName() {
        return addSubscriptionParams.getApiName();
    }

    @Override
    public String getApiVersion() {
        return addSubscriptionParams.getApiVersion();
    }

    @Override
    public String getProvider() {
        return addSubscriptionParams.getProvider();
    }

    @Override
    public String getApplicationName() {
        return addSubscriptionParams.getApplicationName();
    }

    @Override
    public ApiTierAvailability getTier() {
        return addSubscriptionParams.getTier();
    }

    @Override
    public String getApplicationDescription() {
        return addApplicationParams.getApplicationDescription();
    }

    @Override
    public ApiKeyType getKeyType() {
        return generateApplicationKeyParams.getKeyType();
    }

    @Override
    public String getCallbackUrl() {
        return addApplicationParams.getCallbackUrl();
    }

    @Override
    public List<String> getAuthorizedDomains() {
        return generateApplicationKeyParams.getAuthorizedDomains();
    }

    @Override
    public int getValidityTimeInSeconds() {
        return generateApplicationKeyParams.getValidityTimeInSeconds();
    }

    public void setApiName(String apiName) {
        addSubscriptionParams.setApiName(apiName);
    }

    public void setApiVersion(String apiVersion) {
        addSubscriptionParams.setApiVersion(apiVersion);
    }

    public void setProvider(String provider) {
        addSubscriptionParams.setProvider(provider);
    }

    public void setApplicationName(String applicationName) {
        addSubscriptionParams.setApplicationName(applicationName);
    }

    public void setTier(ApiTierAvailability tier) {
        addSubscriptionParams.setTier(tier);
    }

    public void setApplicationDescription(String applicationDescription) {
        addApplicationParams.setApplicationDescription(applicationDescription);
    }

    public void setKeyType(ApiKeyType keyType) {
        generateApplicationKeyParams.setKeyType(keyType);
    }

    public void setCallbackUrl(String callbackUrl) {
        addApplicationParams.setCallbackUrl(callbackUrl);
    }

    public void setAuthorizedDomains(String... authorizedDomains) {
        setAuthorizedDomains(Arrays.asList(authorizedDomains));
    }

    public void setAuthorizedDomains(List<String> authorizedDomains) {
        generateApplicationKeyParams.setAuthorizedDomains(authorizedDomains);
    }

    public void setValidityTimeInSeconds(int validityTimeInSeconds) {
        generateApplicationKeyParams.setValidityTimeInSeconds(validityTimeInSeconds);
    }

}
