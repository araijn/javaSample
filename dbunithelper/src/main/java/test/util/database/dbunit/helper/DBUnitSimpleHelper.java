package test.util.database.dbunit.helper;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;


public class DBUnitSimpleHelper extends AbstractDBUnitHelper{

	protected DBUnitSimpleHelper(IDatabaseTester databaseTester, IDataSet dateset) {

		super(databaseTester, dateset);
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
