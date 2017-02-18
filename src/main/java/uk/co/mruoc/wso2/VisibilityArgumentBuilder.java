package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.ApiVisibility.RESTRICTED;

public class VisibilityArgumentBuilder {

    private final StringArgumentBuilder visibilityNameArgumentBuilder = new StringArgumentBuilder("visibility");
    private final StringArgumentBuilder rolesArgumentBuilder = new StringArgumentBuilder("roles");

    public String build(ApiVisibilityParams params) {
        ApiVisibility visibility = params.getVisibility();
        if (visibility == null)
            return EMPTY;

        String result = formatVisibility(visibility);
        if (isRestricted(visibility))
            result += buildRoles(params);

        return result;
    }

    private String formatVisibility(ApiVisibility visibility) {
        return visibilityNameArgumentBuilder.build(visibility.name().toLowerCase());
    }

    private boolean isRestricted(ApiVisibility visibility) {
        return RESTRICTED.equals(visibility);
    }

    private String buildRoles(ApiVisibilityParams params) {
        return rolesArgumentBuilder.build(params.getRoles());
    }

}
