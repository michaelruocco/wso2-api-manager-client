package uk.co.mruoc.wso2;

public class CredentialsToQueryStringConverter {

    private static final String QUERY_STRING = "?action=login&username=%s&password=%s";

    public String toQueryString(Credentials credentials) {
        return String.format(QUERY_STRING, credentials.getUsername(), credentials.getPassword());
    }

}
