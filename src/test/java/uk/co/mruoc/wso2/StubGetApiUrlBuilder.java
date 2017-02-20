package uk.co.mruoc.wso2;

public class StubGetApiUrlBuilder implements GetApiUrlBuilder {

    private final String urlToReturn;

    public StubGetApiUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build(SelectApiParams params) {
        return urlToReturn;
    }

}
