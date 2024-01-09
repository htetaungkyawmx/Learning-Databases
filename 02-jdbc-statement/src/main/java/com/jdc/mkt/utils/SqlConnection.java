package com.jdc.mkt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/testdb";
	private static final String USER = "root";
	private static final String PASS = "admin";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
