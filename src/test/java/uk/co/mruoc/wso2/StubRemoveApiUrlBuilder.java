package uk.co.mruoc.wso2;

public class StubRemoveApiUrlBuilder implements RemoveApiUrlBuilder {

    private final String urlToReturn;

    public StubRemoveApiUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build(SelectApiParams params) {
        return urlToReturn;
    }

}
