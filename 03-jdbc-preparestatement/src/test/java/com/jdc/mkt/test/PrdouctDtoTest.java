package com.jdc.mkt.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.dto.ProductCountByCategory;
import com.jdc.mkt.service.ProductCountByCategoryService;
import com.jdc.mkt.service.impl.ProductServiceImpl;

public class PrdouctDtoTest {

	static ProductCountByCategoryService pService;
	
	@BeforeAll
	static void init() {
		pService = new ProductServiceImpl();
		
	}
	@Test
	void select() {
		var dto = pService.selectCount();
		for(ProductCountByCategory p :dto)
		System.out.println("Name :"+p.category()+"\tCount :"+p.count());
	}
}
