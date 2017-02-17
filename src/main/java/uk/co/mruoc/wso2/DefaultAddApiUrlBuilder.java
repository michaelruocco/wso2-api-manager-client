package uk.co.mruoc.wso2;

public class DefaultAddApiUrlBuilder implements AddApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final AddApiParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public DefaultAddApiUrlBuilder(String hostUrl) {
        this(hostUrl, new DefaultAddApiParamsToQueryStringConverter());
    }

    public DefaultAddApiUrlBuilder(String hostUrl, AddApiParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    @Override
    public String build(AddApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(AddApiParams params) {
        return queryStringConverter.convert(params);
    }

}
