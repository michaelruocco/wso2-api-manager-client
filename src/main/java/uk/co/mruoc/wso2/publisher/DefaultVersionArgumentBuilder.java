package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.StringArgumentBuilder;

public class DefaultVersionArgumentBuilder {

    private final StringArgumentBuilder argumentBuilder = new StringArgumentBuilder("default_version_checked");

    public String build(AddApiParams params) {
        if (params.isDefaultVersion())
            return argumentBuilder.build("default_version");
        return "";
    }

}
