package uk.co.mruoc.wso2;

public class DefaultGetApiUrlBuilder implements GetApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/listing/ajax/item-list.jag";

    private final GetApiParamsToQueryStringConverter converter = new GetApiParamsToQueryStringConverter();
    private final String url;

    public DefaultGetApiUrlBuilder(String hostUrl) {
        url = hostUrl + RESOURCE_URL;
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
