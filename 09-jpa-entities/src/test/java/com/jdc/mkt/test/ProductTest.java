package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest extends JPAFactory {

	// @ParameterizedTest
	@DisplayName("using persist")
	@CsvSource(value = { "Fruits:Orange:1200" }, delimiter = ':')
	@Order(1)
	void testPersist(String cName, String pName, Double price) {
		// to be transient state
		var c = new Category(cName);
		var p = new Product(pName, price);
		p.setCategory(c);

		em.getTransaction().begin();
		// to be manage from transient
		em.persist(c);
		em.persist(p);
		em.getTransaction().commit();
		assertAll(() -> assertTrue(em.contains(c)), () -> assertTrue(em.contains(p)));

	}

	
	@DisplayName("using find")
	@Test
	@Order(2)
	void testFind() {
		em.getTransaction().begin();
		//to be manage
		var c = em.find(Category.class, 1);
		assertTrue(em.contains(c));
		em.getTransaction().commit();
		//em.close();
		
		String cName = c.getName();
		System.out.println(cName);
	
		String name = c.getProducts().stream().findFirst().get().getName();
		System.out.println(name);
		em.close();
		
		for(Product p : c.getProducts()) {
			System.out.println(p.getName());
		}
		
	}
	
	
	//@Test
	@DisplayName("using detach")
	@Order(3)
	void testDetach() {
		em.getTransaction().begin();
		// to be manage state
		var p = em.find(Product.class, 1);
		assertTrue(em.contains(p));
		p.setName("ss");
		//to be detach state
		em.detach(p);//clear
		assertFalse(em.contains(p));
		// to be managed from detach
		
		
//		var p1 = em.merge(p);
//		assertFalse(em.contains(p));
//		assertTrue(em.contains(p1));
		em.getTransaction().commit();
		
	}
	
	
	//@Test
	@DisplayName("using remove")
	@Order(4)
	void testRemove() {
		em.getTransaction().begin();
		// to be manage state
		var c = em.find(Category.class, 1);
		assertTrue(em.contains(c));
		//to be remove
		em.remove(c);
		assertFalse(em.contains(c));
		
		em.getTransaction().commit();
		
	}
	
	
	
	
	
	
	
}
