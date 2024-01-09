package com.jdc.mkt.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Customer;

public class CustomerTest extends JPAFactory{

	@ParameterizedTest
	@CsvSource({
		"Andrew,22st,aungmyaytharzan,mdy",
		"William,41st,lathar,yangon"
	})
	void insertCustomer(String name,String street,String township,String city) {
		
		em.getTransaction().begin();
		
		var address = new Address(street,township,city);
		em.persist(address);
		
		var customer = new Customer(name);
		customer.addAddress(address);		
		em.persist(customer);
		
		em.getTransaction().commit();
	}
}
