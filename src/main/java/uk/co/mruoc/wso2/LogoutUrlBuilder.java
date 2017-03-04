package uk.co.mruoc.wso2;

public class LogoutUrlBuilder {

    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private final String baseUrl;

    public LogoutUrlBuilder(String hostUrl) {
        baseUrl = hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag";
    }

    public String build() {
        return baseUrl + LOGOUT_QUERY_STRING;
    }

}
