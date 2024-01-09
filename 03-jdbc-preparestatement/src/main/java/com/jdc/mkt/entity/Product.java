package com.jdc.mkt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

	private int id;
	private String name;
	private Double price;
	private Size size;
	private Category catgory;
	private boolean active;
	
	
	public Product( String name,  Double price,  Size size) {
		super();
		this.name = name;
		this.price = price;
		this.size = size;
	}


	public enum Size{
		SMALL,MEDIUM,LARGE
	} 
}
