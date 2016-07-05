package test.util.database.dbunit.init.jdbc;

import test.util.database.dbunit.DBUnitHelperException;
import test.util.database.dbunit.type.DBUnitTesterType;

public class DataSourceReaderFactory {

	public static DataSourceReader createDataSourceReader(DBUnitTesterType type, String configFile) {
		switch(type) {
		case JDBC_PROPERTY: return new PropertyDataSourceReader(configFile);
		default: throw new DBUnitHelperException(type + "is Unimplemented");
		}
	}
}
