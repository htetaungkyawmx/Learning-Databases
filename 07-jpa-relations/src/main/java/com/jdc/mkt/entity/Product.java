package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@Table(name = "product_tbl")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	@ManyToOne
	//@JoinTable(name = "product_cat_tbl")
	private Category category;
	@ManyToMany(mappedBy = "products")
	private List<Customer> customers;
}









