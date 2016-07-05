package test.util.database.dbunit;

import test.util.database.dbunit.type.DBUnitTestFileType;
import test.util.database.dbunit.type.DBUnitTesterType;


public class DBUnitSimpleHelper extends AbstractDBUnitHelper{


	protected DBUnitSimpleHelper(DBUnitTesterType testerType, DBUnitTestFileType testFileType,
			String testDatafile, String configFile) {

		super(testerType, testFileType, testDatafile, configFile);
	}

	@Override
	protected void beforeSetup() throws Exception {}

	@Override
	protected void afterSetup() throws Exception {}

	@Override
	protected void beforeCleanUp() throws Exception {}

	@Override
	protected void afterCleanUp() throws Exception {}

}
