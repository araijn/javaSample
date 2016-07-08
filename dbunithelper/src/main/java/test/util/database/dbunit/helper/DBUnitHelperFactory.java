package test.util.database.dbunit.helper;

import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;

import test.util.database.dbunit.setup.IDataSetFactory;
import test.util.database.dbunit.setup.IDatabaseTesterFactory;
import test.util.database.dbunit.type.FileType;


public class DBUnitHelperFactory {

	/**
	 * 単体試験実施時にテーブルにレコードを追加し、単体試験終了後に追加したレコードを削除したい場合に使用するメソッド
	 * @param testDataFiletype データファイルの種類
	 * @param testDatafile テスト対象のデータファイル
	 * @return
	 */
	public static DBUnitHelper createSimpleHelper(FileType testDataFiletype, String testDatafile) {
		return createSimpleHelper(testDataFiletype, testDatafile, FileType.PROPERTY, null);
	}

	public static DBUnitHelper createSimpleHelper(FileType testDataFiletype, String testDatafile,
			FileType configFileType, String configFile) {
		return createSimpleHelper(
				/* tester */ IDatabaseTesterFactory.createJdbcTester(configFileType, configFile),
				testDataFiletype,
				testDatafile);
	}

	public static DBUnitHelper createSimpleHelper(IDatabaseTester tester, FileType testDataFiletype,
			String testDatafile) {
		return  new DBUnitSimpleHelper(
				tester,
				/* dateset */ IDataSetFactory.createDataSet(testDataFiletype, testDatafile));
	}

	/**
	 * 単体試験実施後にテーブルの状態を実施前に戻したい場合に使用するメソッド
	 * @param testDataFiletype データファイルの種類
	 * @param testDatafile テスト対象のデータファイル
	 * @param tables       バックアップを取得し、リストアする対象のテーブルリスト
	 * @return
	 */
	public static DBUnitHelper createBackupHelper(FileType testDataFiletype, String testDatafile,
			List<String>tables) {
		return createBackupHelper(testDataFiletype, testDatafile, tables, FileType.PROPERTY, null);
	}

	public static DBUnitHelper createBackupHelper(FileType testDataFiletype, String testDatafile,
			List<String>tables, FileType configFileType, String configFile) {
		return createBackupHelper(
				/* tester */ IDatabaseTesterFactory.createJdbcTester(configFileType, configFile),
				testDataFiletype,
				testDatafile,
				tables);
	}

	public static DBUnitHelper createBackupHelper(IDatabaseTester tester, FileType testDataFiletype,
			String testDatafile,  List<String> tables) {
		return  new DBUnitBackupHelper(
				tester,
				/* dataset*/ IDataSetFactory.createDataSet(testDataFiletype, testDatafile),
				tables);
	}
	/**
	 * 単体試験実施後に特定のレコードのみを復旧（登録か削除）をしたい場合に使用するメソッド
	 * @param testDataFiletype データファイルの種類
	 * @param testDataFile    テスト対象のデータファイル
	 * @param insertDataFile  テストを実行後、データベースに追加するレコードのデータファイル
	 * @param deleteDataFile  テストを実行後、データベースから削除するレコードのデータファイル
	 * @return
	 */
	public static DBUnitHelper createAdvanceCleanupHelper(FileType testDataFiletype, String testDatafile,
			String insertDataFile, String deleteDataFile) {
		return createAdvanceCleanupHelper(testDataFiletype, testDatafile, insertDataFile, deleteDataFile,
				FileType.PROPERTY, null);
	}

	public static DBUnitHelper createAdvanceCleanupHelper(FileType testDataFiletype, String testDatafile,
			String insertDataFile, String deleteDataFile, FileType configFileType, String configFile) {
		return createAdvanceCleanupHelper(
				/* tester*/ IDatabaseTesterFactory.createJdbcTester(configFileType, configFile),
				testDataFiletype,
				testDatafile,
				insertDataFile,
				deleteDataFile);
	}

	public static DBUnitHelper createAdvanceCleanupHelper(IDatabaseTester tester, FileType testDataFiletype,
			String testDatafile, String insertDataFile, String deleteDataFile) {
		IDataSet insertdataset = null, deleteDataset = null;

		if(insertDataFile != null) insertdataset = IDataSetFactory.createDataSet(testDataFiletype, insertDataFile);
		if(deleteDataFile != null) deleteDataset = IDataSetFactory.createDataSet(testDataFiletype, deleteDataFile);

		return  new DBUnitAdvanceCleanupHelper(
				tester,
				/* testdateset   */ IDataSetFactory.createDataSet(testDataFiletype, testDatafile),
				insertdataset,
				deleteDataset);

	}

}
