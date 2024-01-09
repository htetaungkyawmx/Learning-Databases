package com.jdc.mkt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Category {

	private int id;
	
	private String name;
	private boolean active;
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	
}
