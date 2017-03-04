package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static uk.co.mruoc.wso2.ApiSubscriptions.SPECIFIC_TENANTS;

public class StatusArgumentBuilder {

    private StringArgumentBuilder argumentBuilder = new StringArgumentBuilder("status");

    public String build(SetStatusApiParams params) {
        ApiStatus status = params.getStatus();
        String result = argumentBuilder.build(formatName(status));
        return result;
    }

    private String formatName(ApiStatus status) {
        if (status == null)
            return EMPTY;
        return status.name().toUpperCase();
    }

}
