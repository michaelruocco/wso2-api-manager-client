package uk.co.mruoc.wso2;

import static uk.co.mruoc.wso2.ApiEndpointType.*;

public class EndpointSecurityArgumentBuilder {

    public String build(EndpointSecurityParams params) {
        String result = formatEndpointType(params);
        if (isSecured(params))
            result +=  buildCredentials(params);
        return result;
    }

    private String formatEndpointType(EndpointSecurityParams params) {
        String result = "endpointSecurity=";
        result += UrlEncoder.encode(params.getEndpointType().name().toLowerCase());
        return result;
    }

    private boolean isSecured(EndpointSecurityParams params) {
        return SECURED.equals(params.getEndpointType());
    }

    private String buildCredentials(EndpointSecurityParams params) {
        return "&epUsername=" + UrlEncoder.encode(params.getEndpointUsername()) +
                "&epPassword=" + UrlEncoder.encode(params.getEndpointPassword());
    }

}
