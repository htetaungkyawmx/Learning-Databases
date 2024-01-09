package com.jdc.mkt.service;

import com.jdc.mkt.entity.Product;

public interface ProductService {

	int insert(Product product);
	int update(Product product);
	Product[] select(Product product);
	void clear();
}
