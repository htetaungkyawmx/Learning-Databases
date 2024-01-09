package com.jdc.mkt.service;

import com.jdc.mkt.entity.Category;

public interface CategoryService {

	int insertCategory(String name);
	int updateCategory(String name,boolean active,int id);
	Category findById(int id);
	void clearCategory();
}
