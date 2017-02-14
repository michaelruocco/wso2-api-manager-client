package uk.co.mruoc.wso2;

import static uk.co.mruoc.wso2.ApiVisibility.RESTRICTED;
import static uk.co.mruoc.wso2.ListToCommaSeparatedStringConverter.toCommaSeparatedString;

public class VisibilityArgumentBuilder {

    public String build(AddApiParams params) {
        String result = formatVisibility(params);
        if (isRestrictedVisibility(params))
            result += buildRoles(params);
        return result;
    }

    private String formatVisibility(AddApiParams params) {
        String result = "&visibility=";
        result += params.getVisibility().name().toLowerCase();
        return result;
    }

    private boolean isRestrictedVisibility(AddApiParams params) {
        ApiVisibility visibility = params.getVisibility();
        return RESTRICTED.equals(visibility);
    }

    private String buildRoles(AddApiParams params) {
        return "&roles=" +  toCommaSeparatedString(params.getRoles());
    }

}
