package uk.co.mruoc.wso2;

public class DefaultLogoutUrlBuilder implements LogoutUrlBuilder {

    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private final String baseUrl;

    public DefaultLogoutUrlBuilder(String hostUrl) {
        baseUrl = hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag";
    }

    @Override
    public String build() {
        return baseUrl + LOGOUT_QUERY_STRING;
    }

}
