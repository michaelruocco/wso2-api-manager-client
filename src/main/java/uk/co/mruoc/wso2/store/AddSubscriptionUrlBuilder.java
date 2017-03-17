package uk.co.mruoc.wso2.store;

public class AddSubscriptionUrlBuilder {

    private static final String RESOURCE_URL = "/store/site/blocks/subscription/subscription-add/ajax/subscription-add.jag";

    private final AddSubscriptionParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public AddSubscriptionUrlBuilder(String hostUrl) {
        this(hostUrl, new AddSubscriptionParamsToQueryStringConverter());
    }

    public AddSubscriptionUrlBuilder(String hostUrl, AddSubscriptionParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(AddSubscriptionParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(AddSubscriptionParams params) {
        return queryStringConverter.convert(params);
    }

}
