package uk.co.mruoc.wso2.store.generateapplicationkey;

public class GenerateApplicationKeyUrlBuilder {

    private static final String RESOURCE_URL = "/store/site/blocks/subscription/subscription-add/ajax/subscription-add.jag";

    private final GenerateApplicationKeyParamsToQueryStringConverter queryStringConverter;
    private final String url;

    public GenerateApplicationKeyUrlBuilder(String hostUrl) {
        this(hostUrl, new GenerateApplicationKeyParamsToQueryStringConverter());
    }

    public GenerateApplicationKeyUrlBuilder(String hostUrl, GenerateApplicationKeyParamsToQueryStringConverter queryStringConverter) {
        this.url = hostUrl + RESOURCE_URL;
        this.queryStringConverter = queryStringConverter;
    }

    public String build(GenerateApplicationKeyParams params) {
        String queryString = queryStringConverter.convert(params);
        return url + queryString;
    }

}
