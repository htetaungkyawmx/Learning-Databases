package com.jdc.mkt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AddressService {

	private static final String URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USER = "root";
	private static final String PASS = "admin";
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
	public int insert(String street,String township,String city) throws SQLException {
		var con = getConnection();
		var stmt = con.createStatement();
		var row = stmt.executeUpdate("insert into address_tbl (street,township,city) values('%s','%s','%s')".formatted(street,township,city));
		return row;
	}
}
