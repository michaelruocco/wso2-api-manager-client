package uk.co.mruoc.wso2;

public class GetApiParamsToQueryStringConverter {

    private static final String QUERY_STRING = "?action=getAPI&name=%s&version=%s&provider=%s";

    public String toQueryString(SelectApiParams params) {
        return String.format(QUERY_STRING, params.getName(), params.getVersion(), params.getProvider());
    }

}
