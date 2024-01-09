package com.jdc.mkt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jdc.mkt.entity.Person;

public class PersonService {

	private static final String URL = "jdbc:mysql://localhost:3306/testdb";
	@SuppressWarnings("unused")
	private static final String USER = "root";
	@SuppressWarnings("unused")
	private static final String PASS = "admin";
	
	private Connection getConnection() throws SQLException {
		//return DriverManager.getConnection(URL, USER, PASS);
//		Properties p = new Properties();
//		p.put("user", "root");
//		p.put("password", "admin");
//		return DriverManager.getConnection(URL, p);
		
		return DriverManager.getConnection(URL+"?user=root&password=admin");
	}
	
	public int insert(String name) throws SQLException {
		var con = getConnection();
		var stmt = con.createStatement();
		var row = stmt
				.executeUpdate("insert into person_tbl (name) values ('%s')".formatted(name));
		return row;
	}
	
	public int update(String name,int id) throws SQLException {
		var con = getConnection();
		var stmt = con.createStatement();
		var row = stmt
				.executeUpdate("update person_tbl set name ='%s' where id = %d ".formatted(name,id));
		return row;
	}
	
	
	public int delete(int id) throws SQLException {
		var con = getConnection();
		var stmt = con.createStatement();
		var row = stmt
				.executeUpdate("delete from person_tbl where id = %d ".formatted(id));
		return row;
	}
	
	public Person findById(int id) throws SQLException {
		String sql = "select * from person_tbl where id = %d".formatted(id);
		try(
		var con = getConnection();
		var stmt = con.createStatement()){
			var rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				var p = new Person();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				return p;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void clear() throws SQLException {
		var con = getConnection();
		var stmt = con.createStatement();
		stmt.executeUpdate("truncate table person_tbl");
		
	}
	
	
	
}
