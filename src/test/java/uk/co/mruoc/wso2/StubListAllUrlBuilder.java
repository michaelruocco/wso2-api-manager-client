package uk.co.mruoc.wso2;

public class StubListAllUrlBuilder implements ListAllUrlBuilder {

    private final String urlToReturn;

    public StubListAllUrlBuilder(String urlToReturn) {
        this.urlToReturn = urlToReturn;
    }

    @Override
    public String build() {
        return urlToReturn;
    }

}
