package test.util.database.dbunit;

import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

import test.util.database.dbunit.init.DataSetFactory;
import test.util.database.dbunit.type.DBUnitTestFileType;
import test.util.database.dbunit.type.DBUnitTesterType;

public class DBUnitAdvanceCleanupHelper extends AbstractDBUnitHelper {

	protected final IDataSet insertDataSet;
	protected final IDataSet deleteDataSet;

	protected DBUnitAdvanceCleanupHelper(DBUnitTesterType testerType, DBUnitTestFileType testFileType,
			String testDatafile, String configFile,	String insertDataFile, String deleteDataFile) {
		super(testerType, testFileType, testDatafile, configFile);
		this.insertDataSet = DataSetFactory.createDataSet(testFileType, insertDataFile);
		this.deleteDataSet = DataSetFactory.createDataSet(testFileType, deleteDataFile);;
	}

	@Override
	protected void beforeSetup() throws Exception {}

	@Override
	protected void afterSetup() throws Exception {}

	@Override
	protected void beforeCleanUp() throws Exception {}

	@Override
	protected void afterCleanUp() throws Exception {

		databaseTester.setDataSet(deleteDataSet);
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
		databaseTester.onTearDown();

		databaseTester.setDataSet(insertDataSet);
		databaseTester.setTearDownOperation(DatabaseOperation.INSERT);
		databaseTester.onTearDown();
	}

}
