package uk.co.mruoc.wso2;

public class LoginUrlBuilder {

    private final CredentialsToQueryStringConverter converter = new CredentialsToQueryStringConverter();
    private final String url;

    public LoginUrlBuilder(String url) {
        this.url = url;
    }

    public String build(Credentials credentials) {
        String queryString = buildLoginQueryString(credentials);
        return url + queryString;
    }

    private String buildLoginQueryString(Credentials credentials) {
        return converter.toQueryString(credentials);
    }

}
