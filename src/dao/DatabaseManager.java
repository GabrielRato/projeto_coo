package dao;

import dao.JDBC.DB;

public class DatabaseManager extends JDBC{
	
	private static final String PASSWORD = "6494";
	private static final String USER = "root";
	private static final String HOST = "localhost";
	private static final String DB_NAME = "coo2017?autoReconnect=true&useSSL=false";
	
	
	public DatabaseManager() {
		super(DB.MYSQL);
	}
	protected String getUser() {
		return USER;
	}

	@Override
	protected String getPassword() {
		return PASSWORD;
	}

	@Override
	protected String getDbHost() {
		return HOST;
	}

	@Override
	protected String getDbName() {
		return DB_NAME;
	}
	
}
