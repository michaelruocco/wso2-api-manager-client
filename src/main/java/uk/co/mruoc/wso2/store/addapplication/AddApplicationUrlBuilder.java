package uk.co.mruoc.wso2.store.addapplication;

public class AddApplicationUrlBuilder {

    private static final String RESOURCE_URL = "/store/site/blocks/application/application-add/ajax/application-add.jag";

    private final AddApplicationParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public AddApplicationUrlBuilder(String hostUrl) {
        this(hostUrl, new AddApplicationParamsToQueryStringConverter());
    }

    public AddApplicationUrlBuilder(String hostUrl, AddApplicationParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(AddApplicationParams params) {
        String queryString = buildQueryString(params);
        return url + queryString;
    }

    private String buildQueryString(AddApplicationParams params) {
        return queryStringConverter.convert(params);
    }

}
