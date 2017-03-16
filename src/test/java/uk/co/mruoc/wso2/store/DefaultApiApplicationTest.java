package uk.co.mruoc.wso2.store;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultApiApplicationTest {

    private final DefaultApiApplication application = new DefaultApiApplication();

    @Test
    public void getApplicationNameShouldDefaultToEmpty() {
        assertThat(application.getApplicationName()).isEmpty();
    }

    @Test
    public void shouldSetApplicationName() {
        String applicationName = "applicationName";

        application.setApplicationName(applicationName);

        assertThat(application.getApplicationName()).isEqualTo(applicationName);
    }

    @Test
    public void getApplicationIdShouldDefaultToZero() {
        assertThat(application.getApplicationId()).isEqualTo(0);
    }

    @Test
    public void shouldSetApplicationId() {
        int id = 10;

        application.setApplicationId(id);

        assertThat(application.getApplicationId()).isEqualTo(id);
    }

}
