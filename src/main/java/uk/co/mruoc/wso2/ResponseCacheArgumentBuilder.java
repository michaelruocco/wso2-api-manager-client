package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ResponseCacheArgumentBuilder {

    private final StringArgumentBuilder cacheArgumentBuilder = new StringArgumentBuilder("responseCache");
    private final StringArgumentBuilder timeoutArgumentBuilder = new StringArgumentBuilder("cacheTimeout");

    public String build(ResponseCacheParams params) {
        if (params.isResponseCacheEnabled())
            return buildCacheEnabled(params);
        return EMPTY;
    }

    private String buildCacheEnabled(ResponseCacheParams params) {
        String result = cacheArgumentBuilder.build("enabled");
        result += timeoutArgumentBuilder.build(Integer.toString(params.getResponseCacheTimeout()));
        return result;
    }

}
