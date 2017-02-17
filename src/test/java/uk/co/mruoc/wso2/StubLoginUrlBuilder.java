package uk.co.mruoc.wso2;

public class StubLoginUrlBuilder implements LoginUrlBuilder {

    private final String urlToReturn;

    public StubLoginUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build(Credentials credentials) {
        return urlToReturn;
    }

}
