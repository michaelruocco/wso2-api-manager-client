package uk.co.mruoc.wso2;

public class StubAuthenticationUrlBuilder implements AuthenticationUrlBuilder {

    private final String loginUrlToReturn;
    private final String logoutUrlToReturn;

    public StubAuthenticationUrlBuilder(String loginUrlToReturn, String logoutUrlToReturn) {
        this.loginUrlToReturn = loginUrlToReturn;
        this.logoutUrlToReturn = logoutUrlToReturn;
    }

    @Override
    public String buildLoginUrl(Credentials credentials) {
        return loginUrlToReturn;
    }

    @Override
    public String buildLogoutUrl() {
        return logoutUrlToReturn;
    }

}
