package test.util.database.dbunit.helper;

import java.sql.Connection;

public interface DBUnitHelper {

	public void setup() ;

	public void cleanUp() ;

	public Connection getConnection() ;
}
