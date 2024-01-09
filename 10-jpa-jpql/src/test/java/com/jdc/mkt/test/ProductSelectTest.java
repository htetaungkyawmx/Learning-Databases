package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.entity.Product;

public class ProductSelectTest extends JPAFactory{

	//@ParameterizedTest
	@CsvSource({"Milk,1","Eggs,2"})
	void selectProductByName(String name,int id) {
		var query = em.createQuery("select p from Product p where p.name = :name",Product.class);
		query.setParameter("name", name);
		var product = query.getSingleResult();
		System.out.println("Category name :"+product.getCategory().getName());
		assertEquals(id, product.getId());
	}
	//@ParameterizedTest
	@CsvSource({"2_000,4_000"})
	void selectProductWithBetween(int less,int greater) {
		var query = em.createQuery("select p from Product p where p.dtPrice between :less and :greater",Product.class);
		query.setParameter("less", less);
		query.setParameter("greater", greater);
		var stream = query.getResultStream();
		stream.forEach(a ->System.out.println("Name :%s\tPrice :%s"
				.formatted(a.getName(),a.getDtPrice())));
	}
	
	
	//@ParameterizedTest
	@CsvSource({"Diary,Snacks"})
	void selectProductByCategoryIn(String name1,String name2) {
		var query = em.createQuery("select p from Product p where p.category.name in(:name1,:name2)",Product.class);
		query.setParameter("name1", name1);
		query.setParameter("name2", name2);
		var stream = query.getResultStream();
		stream.forEach(a ->System.out.println("Category Name :%s\tProduct Name :%s\tPrice :%s"
				.formatted(a.getCategory().getName(),a.getName(),a.getDtPrice())));
	}
	
	//@ParameterizedTest
	@CsvSource({"P","a"})
	void selectProductWithLikeSearch(String name) {
		var query = em.createQuery("select p from Product p where lower(p.name) like lower(:name) order by p.name asc",Product.class);
		query.setParameter("name", name.concat("%"));
		var stream = query.getResultStream();
		stream.forEach(a ->System.out.println("Category Name :%s\tProduct Name :%s\tPrice :%s"
				.formatted(a.getCategory().getName(),a.getName(),a.getDtPrice())));
	}
	
		@ParameterizedTest
		@ValueSource(ints = 2_000)
		void selectProductWithNestedQuery(int dtPrice) {
			var query = em.createQuery("select p from Product p where p.dtPrice = (select max(p.dtPrice) from Product p) ",Product.class);
			var p = query.getSingleResult();
			System.out.println(p.getName());
		}
	
}
