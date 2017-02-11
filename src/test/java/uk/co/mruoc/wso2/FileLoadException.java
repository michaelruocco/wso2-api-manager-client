package uk.co.mruoc.wso2;

public class FileLoadException extends RuntimeException {

    public FileLoadException(Throwable cause) {
        super(cause);
    }

    public FileLoadException(String message, Throwable cause) {
        super(message, cause);
    }

}
