package uk.co.mruoc.wso2;

public class StubUpdateApiParamsToQueryStringConverter implements UpdateApiParamsToQueryStringConverter {

    private final String queryStringToReturn;

    public StubUpdateApiParamsToQueryStringConverter(String queryStringToReturn) {
        this.queryStringToReturn = queryStringToReturn;
    }

    @Override
    public String convert(UpdateApiParams params) {
        return queryStringToReturn;
    }

}
