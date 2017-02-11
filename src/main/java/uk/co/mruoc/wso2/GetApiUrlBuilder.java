package uk.co.mruoc.wso2;

public class GetApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/listing/ajax/item-list.jag";

    private final String url;

    public GetApiUrlBuilder(String hostUrl) {
        url = hostUrl + RESOURCE_URL;
    }

    public String build(GetApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(GetApiParams params) {
        return new GetApiQueryStringBuilder()
                .setName(params.getName())
                .setProvider(params.getProvider())
                .setVersion(params.getVersion())
                .build();
    }

}
