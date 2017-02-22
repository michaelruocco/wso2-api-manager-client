package uk.co.mruoc.wso2;

public class DefaultRemoveApiUrlBuilder implements RemoveApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/remove.jag";

    private final RemoveApiParamsToQueryStringConverter converter;
    private final String url;

    public DefaultRemoveApiUrlBuilder(String hostUrl) {
        this(hostUrl, new DefaultRemoveApiParamsToQueryStringConverter());
    }

    public DefaultRemoveApiUrlBuilder(String hostUrl, RemoveApiParamsToQueryStringConverter converter) {
        this.url = hostUrl + RESOURCE_URL;
        this.converter = converter;
    }

    @Override
    public String build(SelectApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(SelectApiParams params) {
        return converter.convert(params);
    }

}
