package uk.co.mruoc.wso2;

public class AuthenticationUrlBuilder {

    private static final String LOGIN_QUERY_STRING = "?action=login&username=%s&password=%s";
    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private final String baseUrl;

    public AuthenticationUrlBuilder(String hostUrl) {
        baseUrl = hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag";
    }
    public String buildLoginUrl(Credentials credentials) {
        String queryString = buildLoginQueryString(credentials);
        return baseUrl + queryString;
    }

    public String buildLogoutUrl() {
        return baseUrl + LOGOUT_QUERY_STRING;
    }

    private String buildLoginQueryString(Credentials credentials) {
        return String.format(LOGIN_QUERY_STRING, credentials.getUsername(), credentials.getPassword());
    }

}
