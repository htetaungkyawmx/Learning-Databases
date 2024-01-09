package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class ProductRelationTest extends JPAFactory {

	// @Test
	void testManyToOne() {
		var p = em.find(Product.class, 32);
		assertNotNull(p);
		assertAll(() -> assertEquals("Body Wash soap", p.getName()), () -> assertEquals(2200, p.getDtPrice())

		);
	}

	// @Test
	void testManyOneWithJpql() {
		var query = em.createQuery("select p from Product p where p.id = :id", Product.class);
		query.setParameter("id", 1);
		var p = query.getSingleResult();
		assertNotNull(p);
		assertAll(() -> assertEquals("Milk", p.getName()), () -> assertEquals(2500, p.getDtPrice())

		);
	}

	// @Test
	void testOneToMany() {
		// var c = em.find(Category.class, 7);
		var query = em.createQuery("select c from Category c where c.id = :id", Category.class);
		query.setParameter("id", 1);
		var c = query.getSingleResult();
		assertNotNull(c);

	}

	// @Test
	void selectProductByCategoryName() {
		var query = em.createQuery("select p from Product p where p.category.name = :name");
		query.setParameter("name", "Snacks");
		var p = query.getResultStream().findFirst().get();
		assertNotNull(p);

	}

	@Test
	void testCategoryWithProduct() {
		// var c = em.find(Category.class, 7);
		var query = em.createQuery("select c from Category c join Product p where c.id = :id", Category.class);
		query.setParameter("id", 1);
		var c = query.getSingleResult();
		assertNotNull(c);

	}
	

}
