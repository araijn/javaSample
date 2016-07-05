package test.util.database.dbunit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import test.util.database.dbunit.type.DBUnitTestFileType;
import test.util.database.dbunit.type.DBUnitTesterType;

public class DBUnitBackupHelper extends AbstractDBUnitHelper{

	private static final String BACKUP_PREFIX = "backup_dbunit_";
	private static final String BACKUP_SUFFIX = ".xml";

	protected final List<String> backupTables;

	protected final File backFile;

	protected DBUnitBackupHelper(DBUnitTesterType testerType, DBUnitTestFileType testFileType,
			String testDatafile, String configFile, List<String> backupTables) {
		super(testerType, testFileType, testDatafile, configFile);
		this.backupTables = new ArrayList<String>();
		this.backupTables.addAll(backupTables);

		try {
			/* バックアップファイルのパスを作成 */
			this.backFile = File.createTempFile(BACKUP_PREFIX, BACKUP_SUFFIX);
		} catch (IOException e) {
			throw new DBUnitHelperException(e);
		}
	}

	@Override
	protected void beforeSetup() throws Exception {

		QueryDataSet currentDataSet = new QueryDataSet(databaseTester.getConnection());
		for(String tableName : backupTables) {
			currentDataSet.addTable(tableName);
		}

		/* 指定されたテーブルをバックアップする */
		FlatXmlDataSet.write(currentDataSet, new FileOutputStream(backFile));

		/* バックアップ対象のテーブルは削除する */
		DatabaseOperation.TRUNCATE_TABLE.execute(databaseTester.getConnection(), currentDataSet);
	}

	@Override
	protected void afterSetup() throws Exception {}

	@Override
	protected void beforeCleanUp() throws Exception {}

	@Override
	protected void afterCleanUp() throws Exception {

		/* バックアップした内容をリストアする */
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		IDataSet dataSet = builder.build(backFile);
		DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet);

	}


}