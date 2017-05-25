package com.marcin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HsqlConnect {
	
	public Connection connection;
    private PreparedStatement statement;
    public static HsqlConnect db;
	
	private HsqlConnect(){
		
		String url= "jdbc:hsqldb:hsql://127.0.0.1:9001/";
        String dbName = "test-db";
        String driver = "org.hsqldb.jdbcDriver";
        String userName = "SA";
        String password = "";
        
        try {
            Class.forName(driver).newInstance();
            this.connection = (Connection)DriverManager.getConnection(url + dbName, userName, password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }	
	}
	
	 public static synchronized HsqlConnect getDbCon() {
	        if (db == null) {
	            db = new HsqlConnect();
	        }
	        return db;
	 }
	 
	 public ResultSet query(String query) throws SQLException{
	        statement = db.connection.prepareStatement(query);
	        ResultSet res = statement.executeQuery();
	        return res;
	 }
	 
	public int update(String insertQuery) throws SQLException {
		statement = db.connection.prepareStatement(insertQuery);
		int result = statement.executeUpdate();
		return result;
	}

}
