package test.util.database.dbunit;

import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

import test.util.database.dbunit.init.DataSetFactory;
import test.util.database.dbunit.init.DatabaseTesterFactory;
import test.util.database.dbunit.type.DBUnitTestFileType;
import test.util.database.dbunit.type.DBUnitTesterType;

public abstract class AbstractDBUnitHelper implements DBUnitHelper {

	protected final IDataSet dataset;
	protected final IDatabaseTester databaseTester;

	protected AbstractDBUnitHelper(DBUnitTesterType testerType, DBUnitTestFileType testFileType,
			String testDatafile, String configFile) {

		/* Testerの作成 */
		databaseTester = DatabaseTesterFactory.createTester(testerType, configFile);
		dataset = DataSetFactory.createDataSet(testFileType, testDatafile);
	}

	@Override
	public void setup() {
		try {
			beforeSetup();

			doSetup();

			afterSetup();
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	@Override
	public void cleanUp() {
		try {
			beforeCleanUp();

			doCleanUp();

			afterCleanUp();
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	@Override
	public Connection getConnection() {
		try {
			return databaseTester.getConnection().getConnection();
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	protected void doSetup() throws Exception {
		databaseTester.setDataSet(dataset);
		databaseTester.setSetUpOperation(DatabaseOperation.DELETE);
		databaseTester.onSetup();
		databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
		databaseTester.onSetup();
	}

	protected void doCleanUp() throws Exception {
		databaseTester.setDataSet(dataset);
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
		databaseTester.onTearDown();
	}

	protected abstract void beforeSetup() throws Exception;
	protected abstract void afterSetup() throws Exception;
	protected abstract void beforeCleanUp() throws Exception;
	protected abstract void afterCleanUp() throws Exception;
}
