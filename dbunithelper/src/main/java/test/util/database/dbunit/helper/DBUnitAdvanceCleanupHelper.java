package test.util.database.dbunit.helper;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitAdvanceCleanupHelper extends AbstractDBUnitHelper {

	protected final IDataSet insertDataSet;
	protected final IDataSet deleteDataSet;

	protected DBUnitAdvanceCleanupHelper(IDatabaseTester databaseTester, IDataSet testdateset, IDataSet insertdataset, IDataSet deleteDataset) {
		super(databaseTester, testdateset);
		this.insertDataSet = insertdataset;
		this.deleteDataSet = deleteDataset;
	}

	@Override
	protected void beforeSetup(){}

	@Override
	protected void afterSetup(){}

	@Override
	protected void beforeCleanUp() {}

	@Override
	protected void afterCleanUp() {

		try {
			if(deleteDataSet != null) {
				databaseTester.setDataSet(deleteDataSet);
				databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
				databaseTester.onTearDown();
			}

			if(insertDataSet != null) {
				databaseTester.setDataSet(insertDataSet);
				databaseTester.setTearDownOperation(DatabaseOperation.INSERT);
				databaseTester.onTearDown();
			}
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

}
