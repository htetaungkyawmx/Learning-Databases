package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.mkt.entity.Product;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest extends JPAFactory {

	@Test
	@Order(1)
	void selectProductWithResultListAndTypedQuery() {
		//var query = em.createQuery("select p from Product p", Product.class);
		var query = em.createNamedQuery("product.getAllProduct", Product.class);
		List<Product> list = query.getResultList();
		assertEquals(32, list.size());
		
	}

	//@Test
	@Order(2)
	void selectProductCountWithSingleResult() {
		//var query = em.createQuery("select count(p) from Product p",Long.class);
		var query = em.createNamedQuery("product.getProductCount",Long.class);
		Long result = query.getSingleResult();
		System.out.println(result);

	}
	//@Test
	@Order(3)
	void updateProduct() {
		em.getTransaction().begin();
		//var query = em.createQuery("update Product p set p.name = :name,p.dtPrice = :price where p.id = :id");
		var query = em.createNamedQuery("product.updateProductById");
		query.setParameter("name", "test");
		query.setParameter("price", 4000);
		query.setParameter("id", 1);
		int row = query.executeUpdate();
		assertEquals(1, row);
		em.getTransaction().commit();
	}
	//@Test
	@Order(4)
	void deleteProductByName() {
		em.getTransaction().begin();
		//var query = em.createQuery("delete Product p where p.name = ?1");
		//query.setParameter(1, "Red level");
		
		var query = em.createQuery("delete Product p where p.name = :name");
		query.setParameter("name", "Red level");
		var row = query.executeUpdate();
		assertEquals(1, row);
		em.getTransaction().commit();
	}
	
	
	
	
	
	
	
}
