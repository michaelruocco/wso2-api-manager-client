package uk.co.mruoc.wso2;

public class GetApiUrlBuilder {

    private static final String QUERY_STRING = "?action=getAPI&name=%s&version=%s&provider=%s";

    private final String url;

    public GetApiUrlBuilder(String hostUrl) {
        url = hostUrl + "/publisher/site/blocks/listing/ajax/item-list.jag";
    }

    public String build(GetApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(GetApiParams params) {
        return String.format(QUERY_STRING, params.getName(), params.getVersion(), params.getProvider());
    }

}
