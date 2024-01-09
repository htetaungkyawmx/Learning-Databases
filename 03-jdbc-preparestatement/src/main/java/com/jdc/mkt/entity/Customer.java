package com.jdc.mkt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {

	private int id;
	@NonNull
	private String name;
	private Boolean active;
	private Address address;
}
