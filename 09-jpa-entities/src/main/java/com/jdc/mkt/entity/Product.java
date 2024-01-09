package com.jdc.mkt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jdc.mkt.listener.EnableTimesListener;
import com.jdc.mkt.listener.Times;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "product_tbl")
//@EntityListeners(TimesListener.class)
public class Product implements EnableTimesListener{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String name;
	@NonNull
	private Double price;
	@ManyToOne
	private Category category;
	
	private Times times;
}
