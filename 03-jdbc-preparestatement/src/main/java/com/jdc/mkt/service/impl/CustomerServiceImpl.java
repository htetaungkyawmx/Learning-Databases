package com.jdc.mkt.service.impl;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Customer;
import com.jdc.mkt.service.CustomerService;
import static com.jdc.mkt.utils.MysqlConnection.getConnection;

import java.sql.Statement;
import java.util.Arrays;

public class CustomerServiceImpl implements CustomerService{

	private Customer[] customers;
	
	public CustomerServiceImpl() {
		customers = new Customer[0];
	}
	
	@Override
	public int insertCustomer(Customer cu) {
		String sql = "insert into customer_tbl (name,address_id) values (?,?)";
		try(var con = getConnection();
				var stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, cu.getName());
			stmt.setInt(2, insertAddress(cu.getAddress()));
			
			stmt.executeUpdate();
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertAddress(Address a) {
		String sql = "insert into address_tbl (street,township,city) values (?,?,?)";
		try(var con = getConnection();
				var stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1,a.getStreet());
			stmt.setString(2, a.getTownship());
			stmt.setString(3, a.getCity());
			
			stmt.executeUpdate();
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int updateCustomer(Customer cu) {
		return 0;
	}

	@Override
	public Customer[] select(Customer cu) {
		String sql = """
				select c.id,c.name,a.id,a.street,a.township,a.city from customer_tbl c
				join address_tbl a on c.address_id = a.id
				where lower(c.name) like lower(?)
				""";
		
		try(var con = getConnection();
				var stmt = con.prepareStatement(sql)){
			stmt.setString(1,cu.getName());
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var c = new Customer();
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				
				var a = new Address();
				a.setId(rs.getInt("a.id"));
				a.setStreet(rs.getString("a.street"));
				a.setTownship(rs.getString("a.township"));
				a.setCity(rs.getString("a.city"));
				c.setAddress(a);
				addCustomer(c);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	

	@Override
	public void clear() {
		String sql = "truncate table customer_tbl";
		try(var con = getConnection();
				var stmt = con.prepareStatement(sql)){		
			stmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void addCustomer(Customer c) {
		customers = Arrays.copyOf(customers, customers.length+1);
		customers[customers.length-1] = c;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
