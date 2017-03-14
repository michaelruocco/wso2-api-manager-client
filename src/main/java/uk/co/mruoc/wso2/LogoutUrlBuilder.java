package uk.co.mruoc.wso2;

public class LogoutUrlBuilder {

    private static final String LOGOUT_QUERY_STRING = "?action=logout";

    private final String url;

    public LogoutUrlBuilder(String url) {
        this.url = url;
    }

    public String build() {
        return url + LOGOUT_QUERY_STRING;
    }

}
