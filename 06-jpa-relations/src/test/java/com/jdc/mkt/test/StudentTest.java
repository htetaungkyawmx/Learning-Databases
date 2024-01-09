package com.jdc.mkt.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentTest {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("jpaRelations");
	}
	
	@Test
	void test() {
		
	}
}
