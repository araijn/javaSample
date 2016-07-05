package test.util.database.dbunit.init;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import test.util.database.dbunit.DBUnitHelperException;
import test.util.database.dbunit.type.DBUnitTestFileType;


public class DataSetFactory {


	public static IDataSet createDataSet(DBUnitTestFileType type, String testDataFile) {
		switch(type) {
		case XML: return createFlatXmlDataSet(testDataFile);
		case XLS: return createXlsDataSet(testDataFile);
		default: throw new DBUnitHelperException(type + "is Unimplemented");
		}
	}
	private static IDataSet createFlatXmlDataSet(String testDataFile) {
		try {
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);  /* テストデータの先頭にNULL値を持つカラムがある場合設定する */
			return builder.build(ClassLoader.getSystemResourceAsStream(testDataFile));
		} catch (DataSetException e) {
			throw new DBUnitHelperException(e);
		}
	}

	private static IDataSet createXlsDataSet(String testDataFile) {
		try {
			return new XlsDataSet(ClassLoader.getSystemResourceAsStream(testDataFile));
		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}
}
