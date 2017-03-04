package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiStatus.PUBLISHED;

public class SetStatusParamsToQueryStringConverterTest {

    private SetStatusParamsToQueryStringConverter converter = new SetStatusParamsToQueryStringConverter();

    private SetStatusParams params = mock(SetStatusParams.class);

    @Test
    public void shouldConvertStatus() {
        given(params.getStatus()).willReturn(PUBLISHED);

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=updateStatus&status=PUBLISHED");
    }

    @Test
    public void shouldConvertPublishToGateway() {
        given(params.isPublishToGateway()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=updateStatus&publishToGateway=true");
    }

    @Test
    public void shouldConvertRequireSubscription() {
        given(params.isRequireResubscription()).willReturn(true);

        String queryString = converter.convert(params);

        assertThat(queryString).isEqualTo("?action=updateStatus&requireResubscription=true");
    }

}
