package uk.co.mruoc.wso2;

public class DefaultUpdateApiUrlBuilder implements UpdateApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final UpdateApiParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public DefaultUpdateApiUrlBuilder(String hostUrl) {
        this(hostUrl, new DefaultUpdateApiParamsToQueryStringConverter());
    }

    public DefaultUpdateApiUrlBuilder(String hostUrl, UpdateApiParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    @Override
    public String build(UpdateApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(UpdateApiParams params) {
        return queryStringConverter.convert(params);
    }

}
