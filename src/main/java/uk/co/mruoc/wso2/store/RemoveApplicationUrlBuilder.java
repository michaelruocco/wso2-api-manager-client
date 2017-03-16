package uk.co.mruoc.wso2.store;

public class RemoveApplicationUrlBuilder {

    private static final String RESOURCE_URL = "/store/site/blocks/application/application-remove/ajax/application-remove.jag";

    private final NameToRemoveApplicationQueryStringConverter queryStringConverter;
    private final String url;

    public RemoveApplicationUrlBuilder(String hostUrl) {
        this(hostUrl, new NameToRemoveApplicationQueryStringConverter());
    }

    public RemoveApplicationUrlBuilder(String hostUrl, NameToRemoveApplicationQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(String name) {
        String queryString = buildQueryString(name);
        return url + queryString;
    }

    private String buildQueryString(String name) {
        return queryStringConverter.convert(name);
    }

}
