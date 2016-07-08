package test.util.database.dbunit.setup.jdbc;

public interface DataSourceReader {

	public String getDriverClass();

	public String getConnectionUrl();

	public String getUsername();

	public String getPassword();

	public void load() ;
}
