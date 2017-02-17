package uk.co.mruoc.wso2;

public class StubAddApiUrlBuilder implements AddApiUrlBuilder {

    private final String urlToReturn;

    public StubAddApiUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build(AddApiParams params) {
        return urlToReturn;
    }

}
