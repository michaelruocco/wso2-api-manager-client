package uk.co.mruoc.wso2.publisher.getapi;

import uk.co.mruoc.wso2.SelectApiParams;

public class GetApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/listing/ajax/item-list.jag";

    private final String url;
    private final GetApiParamsToQueryStringConverter queryStringConverter;

    public GetApiUrlBuilder(String hostUrl) {
        this(hostUrl, new GetApiParamsToQueryStringConverter());
    }

    public GetApiUrlBuilder(String hostUrl, GetApiParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(SelectApiParams params) {
        String queryString = queryStringConverter.convert(params);
        return url + queryString;
    }

}
