package com.jdc.mkt.service;

import com.jdc.mkt.entity.Customer;

public interface CustomerService {
	
	int insertCustomer(Customer cu);
	int updateCustomer(Customer cu);
	Customer[] select(Customer cu);
	void clear();
	
}
