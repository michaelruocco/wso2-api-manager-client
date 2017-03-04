package uk.co.mruoc.wso2;

public class LoginUrlBuilder {

    private final CredentialsToQueryStringConverter converter = new CredentialsToQueryStringConverter();
    private final String baseUrl;

    public LoginUrlBuilder(String hostUrl) {
        baseUrl = hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag";
    }

    public String build(Credentials credentials) {
        String queryString = buildLoginQueryString(credentials);
        return baseUrl + queryString;
    }

    private String buildLoginQueryString(Credentials credentials) {
        return converter.toQueryString(credentials);
    }

}
