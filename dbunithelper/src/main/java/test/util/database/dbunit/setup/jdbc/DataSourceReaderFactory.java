package test.util.database.dbunit.setup.jdbc;

import test.util.database.dbunit.helper.DBUnitHelperException;
import test.util.database.dbunit.type.FileType;

public class DataSourceReaderFactory {


	public static DataSourceReader createDataSourceReader(FileType configFileType, String configFile) {
		switch(configFileType) {
		case PROPERTY: return new PropertyDataSourceReader(configFile);
		default: throw new DBUnitHelperException(configFileType + "is Unimplemented");
		}
	}
}
