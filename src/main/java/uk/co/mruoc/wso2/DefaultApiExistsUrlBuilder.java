package uk.co.mruoc.wso2;

public class DefaultApiExistsUrlBuilder implements ApiExistsUrlBuilder {

    private static final String RESOURCE_URL = "/publisher/site/blocks/item-add/ajax/add.jag";

    private final NameToExistsQueryStringConverter queryStringConverter;
    private final String url;

    public DefaultApiExistsUrlBuilder(String hostUrl) {
        this(hostUrl, new DefaultNameToApiExistsQueryStringConverter());
    }

    public DefaultApiExistsUrlBuilder(String hostUrl, NameToExistsQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    @Override
    public String build(String name) {
        String queryString = buildQueryString(name);
        return url + queryString;
    }

    private String buildQueryString(String name) {
        return queryStringConverter.convert(name);
    }
    
}
