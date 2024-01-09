package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Customer;
import com.jdc.mkt.service.CustomerService;
import com.jdc.mkt.service.impl.CustomerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class CustomerTest {

	static CustomerService service;
	
	@BeforeAll
	static void init() {
		service = new CustomerServiceImpl();
		service.clear();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/Customer.csv")
	@Order(1)
	void insert(String name,String street,String township,String city,int id) {
		var address = new Address(street,township,city);
		var customer = new Customer(name);
		customer.setAddress(address);
		int result = service.insertCustomer(customer);
		assertEquals(id, result);
	}
	
	@ParameterizedTest
	@ValueSource(ints = 1)
	@Order(2)
	void search(int size) {
		var cu = new Customer("w".concat("%"));
		var ad = new Address();
		cu.setAddress(ad);
		var result = service.select(cu);
		assertEquals(1, result.length);
	}
}











