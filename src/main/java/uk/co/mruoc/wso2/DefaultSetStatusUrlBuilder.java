package uk.co.mruoc.wso2;

public class DefaultSetStatusUrlBuilder implements SetStatusApiUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/life-cycles/ajax/life-cycles.jag";

    private final SetStatusParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public DefaultSetStatusUrlBuilder(String hostUrl) {
        this(hostUrl, new DefaultSetStatusParamsToQueryStringConverter());
    }

    public DefaultSetStatusUrlBuilder(String hostUrl, SetStatusParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    @Override
    public String build(SetStatusParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(SetStatusParams params) {
        return queryStringConverter.convert(params);
    }

}
