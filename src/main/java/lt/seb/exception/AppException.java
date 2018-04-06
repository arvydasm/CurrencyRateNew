package lt.seb.exception;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 2726610379857727129L;

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
