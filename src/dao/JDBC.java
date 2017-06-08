package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class JDBC {

	protected enum DB {
		MYSQL, POSTGRES;
	}

	// MySQL
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql";
	// Postgres
	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String POSTGRES_URL = "jdbc:postgresql";

	private DB db;
	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	protected abstract String getDbHost();

	protected abstract String getDbName();

	protected abstract String getUser();

	protected abstract String getPassword();

	protected JDBC(DB db) {
		this.db = db;
		String dbDriver = this.db == DB.MYSQL ? MYSQL_DRIVER : POSTGRES_DRIVER;
		
		try {
			Class.forName(dbDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openConnection() throws Exception {
		String dbURL = this.db == DB.MYSQL ? MYSQL_URL : POSTGRES_URL;
		
		con = DriverManager.getConnection(dbURL + "://" + getDbHost() + "/"
				+ getDbName(), getUser(), getPassword());
		
	}

	protected void closeConnection() throws Exception {
		if (rs != null) {
			rs.close();
			rs = null;
		}

		if (pstmt != null) {
			pstmt.close();
			pstmt = null;
		}

		if (con != null) {
			con.close();
			con = null;
		}
	}

	protected void commandSQL(String sql) throws Exception {
		pstmt = con.prepareStatement(sql);
	}
}
