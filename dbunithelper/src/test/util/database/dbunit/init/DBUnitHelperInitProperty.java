package test.util.database.dbunit.init;

import java.io.IOException;
import java.util.Properties;

import test.util.database.dbunit.DBUnitHelperException;

public class DBUnitHelperInitProperty {

	private static final String HELPER_INIT_FILE = "DBUnitHelper.ini";

	public enum PropertyKeys {

		DRIVERCLASS("property.driverClass"),
		CONNECTIONURL("property.connectionUrl"),
		USERNAME("property.username"),
		PASSWORD("property.password"),
		;

		private PropertyKeys(String propertyKey){
			this.propertyKey = propertyKey;
		}
		private final String propertyKey;
		public String getPropertyKey() {
			return propertyKey;
		}
	}

	private static final DBUnitHelperInitProperty DBUnitHelperInitProperty = new DBUnitHelperInitProperty();

	private volatile boolean isLoad = false;

	private final Properties initProp = new Properties();

	public static DBUnitHelperInitProperty getInstance() {
		return DBUnitHelperInitProperty;
	}

	private DBUnitHelperInitProperty() {}

	public String getValue(PropertyKeys key) {

		if(!isLoad) {
			load();
			isLoad = true;
		}

		String rval = initProp.getProperty(key.propertyKey);
		if(rval == null) {
			rval = "";
		}
		return rval;
	}

	protected void load() {

		try {
			initProp.load(ClassLoader.getSystemResourceAsStream(HELPER_INIT_FILE));
		} catch (IOException e) {
			throw new DBUnitHelperException(e);
		}
	}
}
