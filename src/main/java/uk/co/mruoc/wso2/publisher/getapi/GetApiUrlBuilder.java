package uk.co.mruoc.wso2.publisher.getapi;

import uk.co.mruoc.wso2.SelectApiParams;

public class GetApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/listing/ajax/item-list.jag";

    private final GetApiParamsToQueryStringConverter converter = new GetApiParamsToQueryStringConverter();
    private final String url;

    public GetApiUrlBuilder(String hostUrl) {
        url = hostUrl + RESOURCE_URL;
    }

    public String build(SelectApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(SelectApiParams params) {
        return converter.convert(params);
    }

}
