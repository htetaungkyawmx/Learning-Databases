package com.jdc.mkt.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.Product.Size;

public class ProductTest extends JpaFactory{

	
	@ParameterizedTest
	@CsvSource({
		"Fruits,Orange,1000,SMALL",
		"Drinks,Cola,700,SMALL",
		"Snacks,fry Sunflower seeds,1200,LARGE"
		})
	void testInsert(String cName,String pName,Integer price,String size) {
		var cat = new Category(cName);
		var prod = new Product(pName,price,Size.valueOf(size));
		
		em.getTransaction().begin();
		em.persist(cat);
		em.persist(prod);
		em.getTransaction().commit();
	}
}
