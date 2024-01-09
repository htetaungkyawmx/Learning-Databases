package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.service.CategoryService;
import com.jdc.mkt.service.impl.CategoryServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class CategoryTest {

	static CategoryService service;
	
	@BeforeAll
	static void init() {
		service = new CategoryServiceImpl();
		service.clearCategory();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Fruits","Drinks"})
	@DisplayName("1. insert into category ")
	@Order(1)
	void insert(String name) {
		var row = service.insertCategory(name);
		assertEquals(1, row);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"Fruit:true:1","Drinks:false:2"},delimiter = ':')
	@DisplayName("2. update into category ")
	@Order(2)
	void update(String name,boolean active,int id) {
		var row = service.updateCategory(name,active,id);
		assertEquals(1, row);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2})
	@DisplayName("3. find By id category ")
	@Order(3)
	void findById(int id) {
		var cat = service.findById(id);
		assertNotNull(cat);
	}
	
}






