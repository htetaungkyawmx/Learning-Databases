package com.jdc.mkt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/testdb";
	
	public static Connection getConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "admin");
		return DriverManager.getConnection(URL, prop);
	}
	
}
