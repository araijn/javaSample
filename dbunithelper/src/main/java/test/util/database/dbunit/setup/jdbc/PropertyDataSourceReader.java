package test.util.database.dbunit.setup.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import test.util.database.dbunit.helper.DBUnitHelperException;
import test.util.database.dbunit.setup.jdbc.DBUnitHelperInitProperty.PropertyKeys;


public class PropertyDataSourceReader implements DataSourceReader {


	private static final String DEFAULT_PROPERTY_NAME = "DBUnitHelper.properties";

	private final String properyFile;
	private volatile DataSourceInfo dataSourceInfo;

	public PropertyDataSourceReader() {
		this(DEFAULT_PROPERTY_NAME);
	}

	public PropertyDataSourceReader(String properyFile) {
		if(properyFile == null) {
			this.properyFile = DEFAULT_PROPERTY_NAME;
		} else {
			this.properyFile = properyFile;
		}
	}

	@Override
	public String getDriverClass() {
		isLoaded();
		return dataSourceInfo.getDriverClass();
	}

	@Override
	public String getConnectionUrl() {
		isLoaded();
		return dataSourceInfo.getConnectionUrl();
	}

	@Override
	public String getUsername() {
		isLoaded();
		return dataSourceInfo.getUserName();
	}

	@Override
	public String getPassword() {
		isLoaded();
		return dataSourceInfo.getPassword();
	}

	@Override
	public void load() {

		this.dataSourceInfo = null;

		try {
			/* 初期化用ファイルの読み込みクラス */
			DBUnitHelperInitProperty initProp = DBUnitHelperInitProperty.getInstance();

			/* プロパティファイルのロード */
			Properties dataSourceProp = new Properties();
			dataSourceProp.load(ClassLoader.getSystemResourceAsStream(properyFile));

			/* ドライバクラスの取得 */
			String driverClass = dataSourceProp.getProperty(initProp.getValue(PropertyKeys.DRIVERCLASS));
			String connectionUrl = dataSourceProp.getProperty(initProp.getValue(PropertyKeys.CONNECTIONURL));
			if(driverClass == null) {
				driverClass = this.findDriverClassName(connectionUrl);
			}

			/* プロパティを取得する */
			dataSourceInfo =  new DataSourceInfo.Builder()
					.driverClass(driverClass)
					.connectionUrl(connectionUrl)
					.userName(dataSourceProp.getProperty(initProp.getValue(PropertyKeys.USERNAME)))
					.password(dataSourceProp.getProperty(initProp.getValue(PropertyKeys.PASSWORD)))
					.build();

		} catch (Exception e) {
			throw new DBUnitHelperException(e);
		}
	}

	protected String findDriverClassName(String connectionUrl) throws SQLException {

		Driver driver = DriverManager.getDriver(connectionUrl);
		if(driver == null) {
			throw new DBUnitHelperException("not find driver : " + connectionUrl);
		}

		String driverStr = driver.toString();
		return driverStr.substring(0, driverStr.indexOf("@"));
	}

	protected void isLoaded() {
		if(dataSourceInfo == null) {
			throw new DBUnitHelperException(properyFile + " is not load!");
		}
	}
}
