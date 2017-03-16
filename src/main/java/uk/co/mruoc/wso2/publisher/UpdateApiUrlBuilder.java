package uk.co.mruoc.wso2.publisher;

public class UpdateApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final UpdateApiParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public UpdateApiUrlBuilder(String hostUrl) {
        this(hostUrl, new UpdateApiParamsToQueryStringConverter());
    }

    public UpdateApiUrlBuilder(String hostUrl, UpdateApiParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(UpdateApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(UpdateApiParams params) {
        return queryStringConverter.convert(params);
    }

}
