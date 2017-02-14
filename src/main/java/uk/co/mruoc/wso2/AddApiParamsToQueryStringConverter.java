package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AddApiParamsToQueryStringConverter {

    public String toQueryString(AddApiParams params) {
        return "?action=addAPI" +
                "&name=" + format(params.getName()) +
                "&context=" + format(params.getDescription()) +
                "&version=" + format(params.getVersion()) +
                "&visibility=" + buildVisibility(params) +
                "&description=" + format(params.getDescription());
    }

    private String format(String value) {
        if (StringUtils.isNotEmpty(value))
            return value;
        return "";
    }

    private String buildVisibility(AddApiParams params) {
        String result = formatVisibility(params);
        if (isRestrictedVisibility(params))
            result += buildRoles(params);
        return result;
    }

    private String formatVisibility(AddApiParams params) {
        ApiVisibility visibility = params.getVisibility();
        String result = visibility.toString();
        return result == null ? "" : result.toLowerCase();
    }

    private boolean isRestrictedVisibility(AddApiParams params) {
        ApiVisibility visibility = params.getVisibility();
        return ApiVisibility.RESTRICTED.equals(visibility);
    }

    private String buildRoles(AddApiParams params) {
        return "&roles=" +  toCommaSeparatedString(params.getRoles());
    }

    private String toCommaSeparatedString(List<String> values) {
        return StringUtils.join(values, ",");
    }

}
