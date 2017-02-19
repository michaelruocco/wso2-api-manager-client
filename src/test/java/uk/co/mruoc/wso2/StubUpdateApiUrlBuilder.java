package uk.co.mruoc.wso2;

public class StubUpdateApiUrlBuilder implements UpdateApiUrlBuilder {

    private final String urlToReturn;

    public StubUpdateApiUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build(UpdateApiParams params) {
        return urlToReturn;
    }

}
