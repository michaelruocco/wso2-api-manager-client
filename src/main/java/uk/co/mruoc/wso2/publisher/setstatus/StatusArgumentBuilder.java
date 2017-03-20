package uk.co.mruoc.wso2.publisher.setstatus;

import uk.co.mruoc.wso2.StringArgumentBuilder;
import uk.co.mruoc.wso2.publisher.ApiStatus;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParams;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class StatusArgumentBuilder {

    private StringArgumentBuilder argumentBuilder = new StringArgumentBuilder("status");

    public String build(SetStatusParams params) {
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
