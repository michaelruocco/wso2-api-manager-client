package uk.co.mruoc.wso2;

public class DefaultLoginUrlBuilder implements LoginUrlBuilder {

    private final CredentialsToQueryStringConverter converter = new CredentialsToQueryStringConverter();
    private final String baseUrl;

    public DefaultLoginUrlBuilder(String hostUrl) {
        baseUrl = hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag";
    }

    @Override
    public String build(Credentials credentials) {
        String queryString = buildLoginQueryString(credentials);
        return baseUrl + queryString;
    }

    private String buildLoginQueryString(Credentials credentials) {
        return converter.toQueryString(credentials);
    }

}
