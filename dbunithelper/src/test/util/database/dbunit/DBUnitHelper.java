package test.util.database.dbunit;

import java.sql.Connection;

public interface DBUnitHelper {

	public void setup() ;

	public void cleanUp() ;

	public Connection getConnection() ;
}
