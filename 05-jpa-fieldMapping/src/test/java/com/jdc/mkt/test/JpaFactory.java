package com.jdc.mkt.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class JpaFactory {

	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("fieldMapping");
	}
	@BeforeEach
	void createEm() {
		em = emf.createEntityManager();
	}
	@AfterAll
	static void closeEmf() {
		if(null != emf && emf.isOpen())
		emf.close();
	}
}
