package uk.co.mruoc.wso2.store.listallapplications;

public class ListAllApplicationsUrlBuilder {

    private static final String RESOURCE = "/store/site/blocks/application/application-list/ajax/application-list.jag";
    private static final String QUERY_STRING = "?action=getApplications";
    private final String url;

    public ListAllApplicationsUrlBuilder(String hostUrl) {
        url = hostUrl + RESOURCE + QUERY_STRING;
    }

    public String build() {
        return url;
    }

}
