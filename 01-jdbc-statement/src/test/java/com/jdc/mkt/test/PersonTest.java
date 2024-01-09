package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.service.AddressService;
import com.jdc.mkt.service.PersonService;

@TestMethodOrder(OrderAnnotation.class)
public class PersonTest {

	static PersonService pService;
	static AddressService aService;
	
	@BeforeAll
	static void init() throws SQLException {
		pService = new PersonService();
		aService = new AddressService();
		pService.clear();
	}
	
	@ParameterizedTest
	@CsvSource({"Andrew","Susan","William"})
	@DisplayName("1.insert into person table")
	@Order(1)
	void testInsertPerson(String name) throws SQLException {
		var row = pService.insert(name);
		assertEquals(1, row);	
	}
	
	@ParameterizedTest
	@CsvSource({"Mr.Andrew,1","Ms.Susan,2","Mr.William,3"})
	@DisplayName("2.update into person table")
	@Order(2)
	void testUpdatePerson(String name,int id) throws SQLException {
		var row = pService.update(name,id);
		assertEquals(1, row);
		
	}
	
	@ParameterizedTest
	@CsvSource({"Mr.Andrew,1","Ms.Susan,2","Mr.William,3"})
	@DisplayName("3.find by person table")
	@Order(3)
	void testFindById(String name,int id) throws SQLException {
		var person = pService.findById(id);
		assertNotNull(person);
		assertEquals(name, person.getName());
	}
	
	//@ParameterizedTest
	@CsvSource({"1","2","3"})
	@DisplayName("4.delete into person table")
	@Order(4)
	void testDeletePerson(int id) throws SQLException {
		var row = pService.delete(id);
		assertEquals(1, row);
		
	}
	
	
	@ParameterizedTest
	@Disabled
	@CsvSource({"19st,Patheingyi,mdy","22st,AungMyayTharzan,mdy"})
	void testAddress(String street,String township,String city) throws SQLException {
		var row = aService.insert(street,township,city);
		assertEquals(1, row);
	}
	
	@AfterAll
	static void close() {}
}
