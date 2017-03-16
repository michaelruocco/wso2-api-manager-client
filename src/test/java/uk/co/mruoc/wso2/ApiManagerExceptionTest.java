package uk.co.mruoc.wso2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiManagerExceptionTest {

    private static final String MESSAGE = "error-message";
    private static final Throwable CAUSE = new Exception();

    @Test
    public void shouldReturnMessage() {
        ApiManagerException exception = new ApiManagerException(MESSAGE);

        assertThat(exception.getMessage()).isEqualTo(MESSAGE);
    }

    @Test
    public void shouldReturnMessageAndCause() {
        ApiManagerException exception = new ApiManagerException(MESSAGE, CAUSE);

        assertThat(exception.getMessage()).isEqualTo(MESSAGE);
        assertThat(exception.getCause()).isEqualTo(CAUSE);
    }

}
