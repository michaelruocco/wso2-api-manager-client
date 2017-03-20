package uk.co.mruoc.wso2.publisher.setstatus;

import org.junit.Test;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParams;
import uk.co.mruoc.wso2.publisher.setstatus.SetStatusParamsToQueryStringConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.publisher.ApiStatus.PUBLISHED;

public class SetStatusParamsToQueryStringConverterTest {

    private static final String PREFIX = "?action=updateStatus";

    private SetStatusParamsToQueryStringConverter converter = new SetStatusParamsToQueryStringConverter();

    private SetStatusParams params = mock(SetStatusParams.class);

    @Test
    public void shouldStartWithPrefix() {
        String result = converter.convert(params);

        assertThat(result).startsWith(PREFIX);
    }

    @Test
    public void shouldConvertStatus() {
        given(params.getStatus()).willReturn(PUBLISHED);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&status=PUBLISHED");
    }

    @Test
    public void shouldConvertPublishToGateway() {
        given(params.isPublishToGateway()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&publishToGateway=true");
    }

    @Test
    public void shouldConvertRequireSubscription() {
        given(params.isRequireResubscription()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).contains("&requireResubscription=true");
    }

}
