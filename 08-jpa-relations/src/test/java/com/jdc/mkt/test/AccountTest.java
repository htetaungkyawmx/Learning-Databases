package com.jdc.mkt.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ocm.jdc.mkt.enity.Address;
import ocm.jdc.mkt.enity.Admin;
import ocm.jdc.mkt.enity.Customer;

public class AccountTest extends JPAFactory{

	@ParameterizedTest
	@CsvSource({
		"Andrew,andrew,123,andrew@gmail,09-231321,22st,hlaing,ygn"
	})
	void insertAccount(String name,String user,String password,String email,String phone,
			String street,String township,String city) {
		
		var customer = new Customer(name,user,password,email,phone);
		var address = new Address(street,township,city);
		customer.setAddress(address);
		var admin = new Admin(name, user, password,"admin");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(address);
		em.persist(customer);
		em.persist(admin);
		em.getTransaction().commit();
		em.close();
		var cu = em.find(Customer.class, 1);
		System.out.println(cu.getPrimaryContact().getPhone());
	}
}
