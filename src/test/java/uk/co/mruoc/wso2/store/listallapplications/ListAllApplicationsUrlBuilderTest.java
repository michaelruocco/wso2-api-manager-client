package uk.co.mruoc.wso2.store.listallapplications;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListAllApplicationsUrlBuilderTest {

    private static final String HOST = "https://localhost:9443";
    private static final String RESOURCE = "/store/site/blocks/application/application-list/ajax/application-list.jag";
    private static final String QUERY_STRING = "?action=getApplications";

    private final ListAllApplicationsUrlBuilder builder = new ListAllApplicationsUrlBuilder(HOST);

    @Test
    public void shouldBuildUrl() {
        String expectedUrl = HOST + RESOURCE + QUERY_STRING;

        String result = builder.build();

        assertThat(result).isEqualTo(expectedUrl);
    }

}
