package uk.co.mruoc.wso2;

public class StubAddApiParamsToQueryStringConverter implements AddApiParamsToQueryStringConverter {

    private final String queryStringToReturn;

    public StubAddApiParamsToQueryStringConverter(String queryStringToReturn) {
        this.queryStringToReturn = queryStringToReturn;
    }

    @Override
    public String convert(AddApiParams params) {
        return queryStringToReturn;
    }

}
