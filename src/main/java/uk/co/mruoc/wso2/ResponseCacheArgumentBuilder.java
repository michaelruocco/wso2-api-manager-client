package uk.co.mruoc.wso2;

public class ResponseCacheArgumentBuilder {

    private static final String PREFIX = "responseCache=";

    public String build(ResponseCacheParams params) {
        if (params.isResponseCacheEnabled())
            return PREFIX + "enabled&cacheTimeout=" + params.getResponseCacheTimeout();
        return PREFIX + "disabled";
    }

}
