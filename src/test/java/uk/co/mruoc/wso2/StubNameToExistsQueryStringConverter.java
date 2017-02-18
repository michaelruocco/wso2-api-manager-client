package uk.co.mruoc.wso2;

public class StubNameToExistsQueryStringConverter implements NameToExistsQueryStringConverter {

    private final String queryStringToReturn;

    public StubNameToExistsQueryStringConverter(String queryStringToReturn) {
        this.queryStringToReturn = queryStringToReturn;
    }

    @Override
    public String convert(String name) {
        return queryStringToReturn;
    }

}
