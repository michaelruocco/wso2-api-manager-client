package uk.co.mruoc.wso2;

public class ResponseCacheArgumentBuilder {

    public String build(AddApiParams params) {
        if (params.isResponseCacheEnabled())
            return "&responseCache=enabled&cacheTimeout=" + UrlEncoder.encode(params.getResponseCacheTimeout());
        return "&responseCache=disabled";
    }

}
