package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class OperationTest extends JPAFactory {

	//@ParameterizedTest
	@CsvSource("Lemon,300,1")
	void testPersist(String name,double price,int catId) {
		
		//transient state
		var p = new Product(name,price);
		var c = new Category();
		c.setId(catId);
		p.setCategory(c);
		
		em.getTransaction().begin();
		//manage state
		em.persist(p);
		//to be detached
		em.clear();
		
		// to be manage using persist
		assertThrows(PersistenceException.class,() -> em.persist(p));
		
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		//to be manage
		var p1 = em.find(Product.class, 1);
		assertTrue(em.contains(p1));
		
		//to be remove
		em.remove(p1);
		assertFalse(em.contains(p1));
		
		//to be manage
		em.persist(p1);
		assertTrue(em.contains(p1));
		
	}
	
	//@Test
	void testMerge() {
		//to be transient
		var p = new Product("Mango",1000.0);
		
		//to be manage
		var p1 = em.merge(p);
		assertTrue(em.contains(p1));
		
		//to be detach
		em.detach(p1);
		assertFalse(em.contains(p1));
		
		//to be manage
		var p2 = em.merge(p1);
		assertTrue(em.contains(p2));
		
		//to be remove
		em.remove(p2);
		assertFalse(em.contains(p2));
		
		//to be manage using merge
		assertThrows(IllegalArgumentException.class, () -> em.merge(p2));
		
	}
	
	@Test
	void testRemove() {
		//to be manage
		var p = em.find(Product.class, 1);
		assertTrue(em.contains(p));
		
		//to be detached
		em.detach(p);
		assertFalse(em.contains(p));
		
		//to be remove from detach
		assertThrows(IllegalArgumentException.class, () -> em.remove(p));
		
	}
	
	
	
	
	
	
}
