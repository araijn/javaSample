package test.util.database.dbunit.helper;

import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public abstract class AbstractDBUnitHelper implements DBUnitHelper {

	protected final IDataSet dataset;
	protected final IDatabaseTester databaseTester;

	protected AbstractDBUnitHelper(IDatabaseTester databaseTester, IDataSet dateset) {
		this.databaseTester = databaseTester;
		this.dataset = dateset;
	}

	@Override
	public void setup() {
		beforeSetup();

		doSetup();

		afterSetup();
	}

	@Override
	public void cleanUp() {
		beforeCleanUp();

		doCleanUp();

		afterCleanUp();
	}

	@Override
	public Connection getConnection() {
		try {
			return databaseTester.getConnection().getConnection();
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	protected void doSetup() {
		try {
			databaseTester.setDataSet(dataset);
			databaseTester.setSetUpOperation(DatabaseOperation.DELETE);
			databaseTester.onSetup();
			databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
			databaseTester.onSetup();
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	protected void doCleanUp() {
		try {
			databaseTester.setDataSet(dataset);
			databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
			databaseTester.onTearDown();
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	protected abstract void beforeSetup()   ;
	protected abstract void afterSetup()    ;
	protected abstract void beforeCleanUp() ;
	protected abstract void afterCleanUp()  ;
}
