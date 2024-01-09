package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class CascadeTest extends JPAFactory{

	@Test
	void testPersist() {
		
		//transient state
		var c = new Category("Snacks");
		var p = new Product("Cookies",6000.0);
		c.addProduct(p);
		
		//to be manage state
		em.getTransaction().begin();
		em.persist(p);
		assertAll(
				() -> assertTrue(em.contains(p)),
				() -> assertTrue(em.contains(c))
				);
		em.getTransaction().commit();
		
	}
	
	//@Test
	void testMerge() {
		//to be manage state
		var c = em.find(Category.class, 1);
		//to be detached state
		em.clear();
		assertFalse(em.contains(c));
		
		//to be manage
		var c1 = em.merge(c);
		assertTrue(em.contains(c1));
		
		for(Product p :c1.getProducts()) {
			assertTrue(em.contains(p));
			
		}
		em.getTransaction().begin();
		//to be remove
		em.remove(c1);
		em.getTransaction().commit();
	}
	
	
	
	
	
	
	
	
}
