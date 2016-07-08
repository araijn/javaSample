package test.util.database.dbunit.setup;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;

import test.util.database.dbunit.helper.DBUnitHelperException;
import test.util.database.dbunit.setup.jdbc.DataSourceReader;
import test.util.database.dbunit.setup.jdbc.DataSourceReaderFactory;
import test.util.database.dbunit.type.FileType;

public class IDatabaseTesterFactory {


	public static IDatabaseTester createJdbcTester(FileType configFileType, String configFile) {

		try {
			/* 設定の読み込み */
			DataSourceReader reader = DataSourceReaderFactory.createDataSourceReader(configFileType, configFile);
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
