package uk.co.mruoc.wso2;

public class DefaultVersionArgumentBuilder {

    public String build(AddApiParams params) {
        if (params.isDefaultVersion())
            return "default_version_checked=default_version";
        return "";
    }

}
