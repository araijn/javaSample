package test.util.database.dbunit;

import java.util.List;

import test.util.database.dbunit.type.DBUnitHelperType;


public class DBUnitHelperFactory {

	/**
	 * 単体試験毎に、試験データを追加し、試験終了後にはそのレコードを削除するような場合に使用する
	 * @param type 作成するTesterの種類
	 * @param testDatafile テスト対象のデータファイル
	 * @return
	 */
	public static DBUnitHelper createSimpleHelper(DBUnitHelperType type, String testDatafile) {
		return createSimpleHelper(type, testDatafile, null);
	}

	public static DBUnitHelper createSimpleHelper(DBUnitHelperType type, String testDatafile, String configFile) {
		return new DBUnitSimpleHelper(type.getTesterType(), type.getTestFileType(), testDatafile, configFile);
	}
	/**
	 * 単体試験を実行する前に、予めテスト対象のテーブルにレコードが存在し
	 * 単体試験後に、テスト前の状態に戻したい場合に使用する
	 * @param type 作成するTesterの種類
	 * @param testDatafile テスト対象のデータファイル
	 * @param tables       バックアップを取得し、リストアする対象のテーブルリスト
	 * @return
	 */
	public static DBUnitHelper createBackupHelper(DBUnitHelperType type, String testDatafile, List<String>tables) {
		return createBackupHelper(type, testDatafile, tables, null);
	}

	public static DBUnitHelper createBackupHelper(DBUnitHelperType type, String testDatafile,
			List<String>tables, String configFile) {

		if(tables == null || tables.isEmpty()) {
			return createSimpleHelper(type, testDatafile, configFile);
		}

		return new DBUnitBackupHelper(type.getTesterType(), type.getTestFileType(), testDatafile, configFile, tables);
	}

	/**
	 * 単体試験を実行した結果、データベースのレコードが追加・削除されるが
	 * Backupの時のように一括で削除、保存されたくない場合に使用する
	 * @param testDataFile   テスト対象のデータファイル
	 * @param insertDataFile  テストを実行後、データベースに追加するレコードのデータファイル
	 * @param deleteDataFile  テストを実行後、データベースから削除するレコードのデータファイル
	 * @return
	 */
	public static DBUnitHelper createAdvanceCleanupHelper(DBUnitHelperType type, String testDataFile,
			String insertDataFile, String deleteDataFile) {
		return createAdvanceCleanupHelper(type, testDataFile, insertDataFile, deleteDataFile, null);
	}

	public static DBUnitHelper createAdvanceCleanupHelper(DBUnitHelperType type, String testDataFile,
			String insertDataFile, String deleteDataFile, String configFile) {
		return new DBUnitAdvanceCleanupHelper(type.getTesterType(), type.getTestFileType(),
				testDataFile, configFile,insertDataFile, deleteDataFile);
	}
}
