package uk.co.mruoc.wso2;

public class NameToApiExistsQueryStringConverter {

    private static final String QUERY_STRING = "?action=isAPINameExist&apiName=%s";

    public String convert(String name) {
        return String.format(QUERY_STRING, name);
    }

}
