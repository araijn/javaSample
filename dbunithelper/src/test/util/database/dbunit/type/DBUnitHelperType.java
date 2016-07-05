package test.util.database.dbunit.type;

public enum DBUnitHelperType {

	/* 接続方法：JDBC データベース接続情報：プロパティファイルより取得 テストデータファイルの形式:XML */
	JDBC_DEFAULT(DBUnitTesterType.JDBC_PROPERTY, DBUnitTestFileType.XML),
	/* 接続方法：JDBC データベース接続情報：プロパティファイルより取得 テストデータファイルの形式:XLS */
	JDBC_XLS(DBUnitTesterType.JDBC_PROPERTY, DBUnitTestFileType.XLS),
	/* 接続方法：JDBC データベース接続情報：XMLファイルより取得 テストデータファイルの形式:XML */
	//JDBC_CONF_XML(DBUnitTesterType.JDBC_XML, DBUnitTestFileType.XML),               /* TODO 未実装 */
	/* 接続方法：JDBC データベース接続情報：XMLファイルより取得 テストデータファイルの形式:XLS */
	//JDBC_CONF_XML_FILE_TYPE_XLS(DBUnitTesterType.JDBC_XML, DBUnitTestFileType.XLS), /* TODO 未実装 */
	/* 接続方法：JNDI テストデータファイルの形式:XML */
	//JNDI(DBUnitTesterType.JNDI, DBUnitTestFileType.XML),                            /* TODO 未実装 */
	/* 接続方法：JNDI テストデータファイルの形式:XLS */
	//JNDI_XLS(DBUnitTesterType.JNDI, DBUnitTestFileType.XLS),                        /* TODO 未実装 */
	;

	private final DBUnitTesterType testerType;
	private final DBUnitTestFileType testFileType;


	DBUnitHelperType(DBUnitTesterType testerType, DBUnitTestFileType testFileType) {
		this.testerType = testerType;
		this.testFileType = testFileType;
		}

	public DBUnitTesterType getTesterType() {
		return testerType;
	}

	public DBUnitTestFileType getTestFileType() {
		return testFileType;
	}

}
