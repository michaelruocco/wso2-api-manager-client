package uk.co.mruoc.wso2;

public class StubApiExistsUrlBuilder implements ApiExistsUrlBuilder {

    private final String urlToReturn;

    public StubApiExistsUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build(String name) {
        return urlToReturn;
    }

}
