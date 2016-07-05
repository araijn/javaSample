package test.util.database.dbunit.init.jdbc;

public class DataSourceInfo {

	private final String driverClass;
	private final String connectionUrl;
	private final String userName;
	private final String password;

	private DataSourceInfo(Builder builder) {
		super();
		this.driverClass = builder.driverClass;
		this.connectionUrl = builder.connectionUrl;
		this.userName = builder.userName;
		this.password = builder.password;
	}

	public String getDriverClass() {
		return driverClass;
	}
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}

	public static class Builder {

		private String driverClass;
		private String connectionUrl;
		private String userName;
		private String password;

		public Builder() {}

		public Builder driverClass(String driverClass) {
			this.driverClass = driverClass;
			return this;
		}

		public Builder connectionUrl(String connectionUrl) {
			this.connectionUrl = connectionUrl;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public DataSourceInfo build() {
			return new DataSourceInfo(this);
		}
	}
}
