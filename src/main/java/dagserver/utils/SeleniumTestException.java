package dagserver.utils;

public class SeleniumTestException extends Exception {
  
	private static final long serialVersionUID = 1L;

	public SeleniumTestException() {
        super();
    }

    public SeleniumTestException(String message) {
        super(message);
    }

    public SeleniumTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeleniumTestException(Throwable cause) {
        super(cause);
    }
}