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
	protected void beforeSetup() throws Exception {}

	@Override
	protected void afterSetup() throws Exception {}

	@Override
	protected void beforeCleanUp() throws Exception {}

	@Override
	protected void afterCleanUp() throws Exception {

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
	}

}
