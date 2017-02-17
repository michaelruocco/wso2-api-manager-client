package uk.co.mruoc.wso2;

import static uk.co.mruoc.wso2.ApiVisibility.RESTRICTED;

public class VisibilityArgumentBuilder {

    public String build(ApiVisibilityParams params) {
        String result = formatVisibility(params);
        if (isRestrictedVisibility(params))
            result += buildRoles(params);
        return result;
    }

    private String formatVisibility(ApiVisibilityParams params) {
        String result = "visibility=";
        result += UrlEncoder.encode(params.getVisibility().name().toLowerCase());
        return result;
    }

    private boolean isRestrictedVisibility(ApiVisibilityParams params) {
        ApiVisibility visibility = params.getVisibility();
        return RESTRICTED.equals(visibility);
    }

    private String buildRoles(ApiVisibilityParams params) {
        return "&roles=" + UrlEncoder.encodeToCommaSeparatedList(params.getRoles());
    }

}
