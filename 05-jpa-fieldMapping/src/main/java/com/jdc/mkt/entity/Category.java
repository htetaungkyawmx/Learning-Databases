package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(generator = "product_gen")
	//@SequenceGenerator(name = "category_gen")
	private int id;
	@NonNull
	@Column(name = "cat_name",
	unique = true,
	nullable = false,
	length = 45)
	private String name;
	@Column(columnDefinition = "tinyint(1) not null default 1")
	private boolean active;
}
