package com.jdc.mkt.service;

import com.jdc.mkt.entity.Category;

public interface CategoryService {

	int insert(Category category);
	int update(Category category);
	Category[] select(Category category);
	void clear();
}
