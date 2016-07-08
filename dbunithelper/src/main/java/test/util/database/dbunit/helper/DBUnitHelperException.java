package test.util.database.dbunit.helper;

@SuppressWarnings("serial")
public class DBUnitHelperException extends RuntimeException {

	public DBUnitHelperException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBUnitHelperException(String message) {
		super(message);
	}

	public DBUnitHelperException(Throwable cause) {
		super(cause);
	}


}
