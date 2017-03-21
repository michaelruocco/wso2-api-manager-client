package uk.co.mruoc.wso2.store.generateapplicationkey;

import uk.co.mruoc.wso2.StringArgumentBuilder;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class GenerateApplicationKeyParamsToQueryStringConverter {

    private final StringArgumentBuilder applicationNameArgumentBuilder = new StringArgumentBuilder("application");
    private final StringArgumentBuilder keyTypeArgumentBuilder = new StringArgumentBuilder("keytype");
    private final StringArgumentBuilder callbackUrlArgumentBuilder = new StringArgumentBuilder("callbackUrl");
    private final StringArgumentBuilder authorizedDomainsArgumentBuilder = new StringArgumentBuilder("authorizedDomains");
    private final StringArgumentBuilder validityTimeArgumentBuilder = new StringArgumentBuilder("validityTime");

    public String convert(GenerateApplicationKeyParams params) {
        return "?action=generateApplicationKey" +
                applicationNameArgumentBuilder.build(params.getApplicationName()) +
                keyTypeArgumentBuilder.build(formatName(params.getKeyType())) +
                callbackUrlArgumentBuilder.build(params.getCallbackUrl()) +
                authorizedDomainsArgumentBuilder.build(params.getAuthorizedDomains()) +
                validityTimeArgumentBuilder.build(Integer.toString(params.getValidityTimeInSeconds())) +
                "&tokenScope";
    }

    private static String formatName(ApiKeyType keyType) {
        if (keyType == null)
            return EMPTY;
        return keyType.name();
    }

}
