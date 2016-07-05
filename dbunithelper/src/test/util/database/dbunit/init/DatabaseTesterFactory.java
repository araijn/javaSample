package test.util.database.dbunit.init;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;

import test.util.database.dbunit.DBUnitHelperException;
import test.util.database.dbunit.init.jdbc.DataSourceReader;
import test.util.database.dbunit.init.jdbc.DataSourceReaderFactory;
import test.util.database.dbunit.type.DBUnitTesterType;

public class DatabaseTesterFactory {

	public static IDatabaseTester createTester(DBUnitTesterType type, String configFile) {

		switch(type) {
		case JDBC_PROPERTY: return createJdbcTester(type, configFile);
		default: throw new DBUnitHelperException(type + "is Unimplemented");
		}
	}

	private static IDatabaseTester createJdbcTester(DBUnitTesterType type, String configFile) {

		try {
			/* 設定の読み込み */
			DataSourceReader reader = DataSourceReaderFactory.createDataSourceReader(type, configFile);
			reader.load();

			return new JdbcDatabaseTester(
					/* driverClass   */ reader.getDriverClass(),
					/* connectionUrl */ reader.getConnectionUrl(),
					/* username      */ reader.getUsername(),
					/* password      */ reader.getPassword());

		} catch (ClassNotFoundException e) {
			throw new DBUnitHelperException(e);
		}

	}
}
