package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.ApiEndpointType.*;

public class EndpointSecurityArgumentBuilder {

    private final StringArgumentBuilder securityArgumentBuilder = new StringArgumentBuilder("endpointSecurity");
    private final StringArgumentBuilder usernameArgumentBuilder = new StringArgumentBuilder("epUsername");
    private final StringArgumentBuilder passwordArgumentBuilder = new StringArgumentBuilder("epPassword");

    public String build(EndpointSecurityParams params) {
        ApiEndpointType endpointType = params.getEndpointType();
        String result = securityArgumentBuilder.build(formatEndpointTypeName(endpointType));
        if (isSecured(endpointType))
            result += buildCredentials(params);
        return result;
    }

    private String formatEndpointTypeName(ApiEndpointType endpointType) {
        if (endpointType == null)
            return EMPTY;
        return endpointType.name().toLowerCase();
    }

    private boolean isSecured(ApiEndpointType endpointType) {
        return SECURED.equals(endpointType);
    }

    private String buildCredentials(EndpointSecurityParams params) {
        String result = usernameArgumentBuilder.build(params.getEndpointUsername());
        result += passwordArgumentBuilder.build(params.getEndpointPassword());
        return result;
    }

}
