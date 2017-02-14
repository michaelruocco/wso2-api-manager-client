package uk.co.mruoc.wso2;

public class AddApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final AddApiParamsToQueryStringConverter converter = new AddApiParamsToQueryStringConverter();
    private final String url;

    public AddApiUrlBuilder(String hostUrl) {
        url = hostUrl + RESOURCE_URL;
    }

    public String build(AddApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    public String buildQueryString(AddApiParams params) {
        return converter.toQueryString(params);
    }

}
