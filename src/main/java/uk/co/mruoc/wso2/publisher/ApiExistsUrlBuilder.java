package uk.co.mruoc.wso2.publisher;

public class ApiExistsUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final NameToApiExistsQueryStringConverter queryStringConverter;
    private final String url;

    public ApiExistsUrlBuilder(String hostUrl) {
        this(hostUrl, new NameToApiExistsQueryStringConverter());
    }

    public ApiExistsUrlBuilder(String hostUrl, NameToApiExistsQueryStringConverter queryStringConverter) {
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
