package uk.co.mruoc.wso2.store.getsubscription;

import uk.co.mruoc.wso2.SelectApiParams;

public class GetSubscriptionUrlBuilder {

    private static final String RESOURCE_URL = "/store/site/blocks/subscription/subscription-list/ajax/subscription-list.jag";

    private final String url;
    private final SelectApiParamsToGetSubscriptionQueryStringConverter queryStringConverter;

    public GetSubscriptionUrlBuilder(String hostUrl) {
        this(hostUrl, new SelectApiParamsToGetSubscriptionQueryStringConverter());
    }

    public GetSubscriptionUrlBuilder(String hostUrl, SelectApiParamsToGetSubscriptionQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(SelectApiParams params) {
        String queryString = queryStringConverter.convert(params);
        return url + queryString;
    }

}
