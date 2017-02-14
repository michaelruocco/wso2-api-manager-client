package uk.co.mruoc.wso2;

import static uk.co.mruoc.wso2.ApiEndpointType.*;

public class EndpointSecurityArgumentBuilder {

    public String build(AddApiParams params) {
        String result = formatEndpointType(params);
        if (isSecured(params))
            result +=  buildCredentials(params);
        return result;
    }

    private String formatEndpointType(AddApiParams params) {
        String result = "&endpointSecurity=";
        result += params.getApiEndpointType().name().toLowerCase();
        return result;
    }

    private boolean isSecured(AddApiParams params) {
        return SECURED.equals(params.getApiEndpointType());
    }

    private String buildCredentials(AddApiParams params) {
        return "&epUsername=" + params.getEndpointUsername() + "&epPassword=" + params.getEndpointPassword();
    }

}
