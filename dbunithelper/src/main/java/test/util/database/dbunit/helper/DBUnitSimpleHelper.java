package test.util.database.dbunit.helper;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;


public class DBUnitSimpleHelper extends AbstractDBUnitHelper{

	protected DBUnitSimpleHelper(IDatabaseTester databaseTester, IDataSet dateset) {

		super(databaseTester, dateset);
	}

	@Override
	protected void beforeSetup(){}

	@Override
	protected void afterSetup() {}

	@Override
	protected void beforeCleanUp(){}

	@Override
	protected void afterCleanUp() {}

}
