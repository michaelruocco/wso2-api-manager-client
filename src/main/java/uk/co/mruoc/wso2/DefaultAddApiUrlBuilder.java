package uk.co.mruoc.wso2;

public class DefaultAddApiUrlBuilder implements AddApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final AddApiParamsToQueryStringConverter converter = new AddApiParamsToQueryStringConverter();
    private final String url;

    public DefaultAddApiUrlBuilder(String hostUrl) {
        url = hostUrl + RESOURCE_URL;
    }

    @Override
    public String build(AddApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(AddApiParams params) {
        return converter.toQueryString(params);
    }

}
