package uk.co.mruoc.wso2.publisher.setstatus;

public class SetStatusUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/life-cycles/ajax/life-cycles.jag";

    private final SetStatusParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public SetStatusUrlBuilder(String hostUrl) {
        this(hostUrl, new SetStatusParamsToQueryStringConverter());
    }

    public SetStatusUrlBuilder(String hostUrl, SetStatusParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(SetStatusParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(SetStatusParams params) {
        return queryStringConverter.convert(params);
    }

}
