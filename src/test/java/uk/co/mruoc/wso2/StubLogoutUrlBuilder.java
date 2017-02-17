package uk.co.mruoc.wso2;

public class StubLogoutUrlBuilder implements LogoutUrlBuilder {

    private final String urlToReturn;

    public StubLogoutUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build() {
        return urlToReturn;
    }

}
