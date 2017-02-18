package uk.co.mruoc.wso2;

public class DefaultNameToExistsQueryStringConverter implements NameToExistsQueryStringConverter {

    private static final String QUERY_STRING = "?action=isAPINameExist&apiName=%s";

    @Override
    public String convert(String name) {
        return String.format(QUERY_STRING, name);
    }

}
