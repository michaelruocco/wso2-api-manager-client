package uk.co.mruoc.wso2.store.removeapplication;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RemoveApplicationUrlBuilderTest {

    private static final String HOST_URL = "https://localhost:8443";
    private static final String RESOURCE = "/store/site/blocks/application/application-remove/ajax/application-remove.jag";
    private static final String QUERY_STRING = "?queryString=value";
    private static final String NAME = "name";

    private final NameToRemoveApplicationQueryStringConverter queryStringConverter = mock(NameToRemoveApplicationQueryStringConverter.class);
    private final RemoveApplicationUrlBuilder builder = new RemoveApplicationUrlBuilder(HOST_URL, queryStringConverter);

    @Test
    public void shouldBuildUrl() {
        given(queryStringConverter.convert(NAME)).willReturn(QUERY_STRING);

        String url = builder.build(NAME);

        assertThat(url).isEqualTo(HOST_URL + RESOURCE + QUERY_STRING);
    }

}
