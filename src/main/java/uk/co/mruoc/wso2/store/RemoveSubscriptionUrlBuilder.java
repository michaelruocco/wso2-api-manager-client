package uk.co.mruoc.wso2.store;

public class RemoveSubscriptionUrlBuilder {

    private static final String RESOURCE_URL = "/store/site/blocks/subscription/subscription-remove/ajax/subscription-remove.jag";

    private final RemoveSubscriptionParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public RemoveSubscriptionUrlBuilder(String hostUrl) {
        this(hostUrl, new RemoveSubscriptionParamsToQueryStringConverter());
    }

    public RemoveSubscriptionUrlBuilder(String hostUrl, RemoveSubscriptionParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(RemoveSubscriptionParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(RemoveSubscriptionParams params) {
        return queryStringConverter.convert(params);
    }

}
