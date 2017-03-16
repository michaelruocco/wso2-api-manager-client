package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.SelectApiParams;

public class RemoveApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/remove.jag";

    private final RemoveApiParamsToQueryStringConverter converter;
    private final String url;

    public RemoveApiUrlBuilder(String hostUrl) {
        this(hostUrl, new RemoveApiParamsToQueryStringConverter());
    }

    public RemoveApiUrlBuilder(String hostUrl, RemoveApiParamsToQueryStringConverter converter) {
        this.url = hostUrl + RESOURCE_URL;
        this.converter = converter;
    }

    public String build(SelectApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(SelectApiParams params) {
        return converter.convert(params);
    }

}
