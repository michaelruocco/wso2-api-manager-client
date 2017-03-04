package uk.co.mruoc.wso2;

public class DefaultSetStatusApiUrlBuilder implements SetStatusApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/life-cycles/ajax/life-cycles.jag";

    private final SetStatusApiParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public DefaultSetStatusApiUrlBuilder(String hostUrl) {
        this(hostUrl, new DefaultSetStatusApiParamsToQueryStringConverter());
    }

    public DefaultSetStatusApiUrlBuilder(String hostUrl, SetStatusApiParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    @Override
    public String build(SetStatusApiParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(SetStatusApiParams params) {
        return queryStringConverter.convert(params);
    }

}
