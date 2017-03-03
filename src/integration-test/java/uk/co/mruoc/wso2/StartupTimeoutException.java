package uk.co.mruoc.wso2;

public class StartupTimeoutException extends RuntimeException {

    public StartupTimeoutException(String startupMessage, int timeout) {
        this("startup message " + startupMessage + " not logged before timeout " + timeout);
    }

    public StartupTimeoutException(String message) {
        super(message);
    }

}
