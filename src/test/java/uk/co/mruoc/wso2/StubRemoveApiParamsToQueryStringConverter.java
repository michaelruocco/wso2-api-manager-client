package uk.co.mruoc.wso2;

public class StubRemoveApiParamsToQueryStringConverter implements RemoveApiParamsToQueryStringConverter {

    private final String queryStringToReturn;

    public StubRemoveApiParamsToQueryStringConverter(String queryStringToReturn) {
        this.queryStringToReturn = queryStringToReturn;
    }

    @Override
    public String convert(SelectApiParams params) {
        return queryStringToReturn;
    }

}
