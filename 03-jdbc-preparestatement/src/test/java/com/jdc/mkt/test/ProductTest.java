package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.Product.Size;
import com.jdc.mkt.service.CategoryService;
import com.jdc.mkt.service.ProductService;
import com.jdc.mkt.service.impl.CategoryServiceImpl;
import com.jdc.mkt.service.impl.ProductServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {

	 static ProductService pService;
	static CategoryService catService;
	
	@BeforeAll
	static void init() {
		pService = new ProductServiceImpl();
		catService = new CategoryServiceImpl();
		pService.clear();	
		//catService.clear();
	}
	@BeforeEach
	void clear() {
		pService = new ProductServiceImpl();
	}
	
	@ParameterizedTest
	@Disabled
	@CsvSource({"1,T-shirt","2,Trouser"})
	@DisplayName("01.insert into category ")
	@Order(1)
	void insertCategory(int id ,String name) {
		var result = catService.insert(new Category(name));
		assertEquals(id,result);
	}
	
	@ParameterizedTest
	@Disabled
	@CsvSource({"1,T-shirts","2,Trouser"})
	@DisplayName("01.Update into category ")
	@Order(1)
	void updateCategory(int id ,String name) {
		var cat = new Category(name);
		cat.setId(id);
		cat.setActive(true);
		var result = catService.update(cat);
		assertEquals(1,result);
	}
	
	@ParameterizedTest
	@CsvSource({"1,T-shirts","2,Trouser"})
	@DisplayName("01.Delete into category ")
	@Disabled
	@Order(2)
	void deleteCategory(int id ,String name) {
		var cat = new Category(name);
		cat.setId(id);
		cat.setActive(false);
		var result = catService.update(cat);
		assertEquals(1,result);
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/product.csv"
	,delimiter = '\t',
	numLinesToSkip = 1)
	@DisplayName("02.insert into product")
	//@Disabled
	@Order(3)
	void insertProduct(int id,String name,double price,String size,int catId) {
		var product = new Product(name,price,Size.valueOf(size));
		var category = new Category();
		category.setId(catId);
		product.setCatgory(category);
		var result = pService.insert(product);
		assertEquals(id, result);
	}
	
	
	
	@ParameterizedTest
	@DisplayName("02.select product with dynamic search")
	@Order(4)
	@CsvSource({
		",,,",
		"T-shirts,,,",
		"T-shirts,h,,",
		",a,,",
		",,,SMALL",
		",,25000,",
		"T-shirts,a,25000,MEDIUM"
	})
	void selectProduct(String catName,String pName,Double price,String size) {
		Size s = null != size ? Size.valueOf(size):null;
		
		var product = new Product();
		product.setName(pName);
		product.setPrice(price);
		product.setSize(s);
		
		var category = new Category();
		category.setName(catName);
		product.setCatgory(category);
		
		var products = pService.select(product);
		show(products);	
		System.out.println();
		
	}
	
	private void show(Product[] products) {
		for(Product p :products)
		System.out.println("Category :%s\tProduct :%s\tPrice :%s\tSize :%s"
				.formatted(p.getCatgory().getName(),
						p.getName(),
						p.getPrice(),
						p.getSize().name()));
		
	}
	
	

	
	
	
	
	
	
	
}
