package uk.co.mruoc.wso2;

public class LoginQueryStringBuilder {

    private static final String QUERY_STRING = "?action=login&username=%s&password=%s";

    private String username;
    private String password;

    public LoginQueryStringBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginQueryStringBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public String build() {
        return String.format(QUERY_STRING, username, password);
    }

}
