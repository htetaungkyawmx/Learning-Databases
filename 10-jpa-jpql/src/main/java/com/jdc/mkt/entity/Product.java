package com.jdc.mkt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name ="product")
@NamedQuery(name = "product.getAllProduct",query = "select p from Product p where p.active = true")
@NamedQuery(name ="product.getProductCount",query ="select count(p) from Product p")
@NamedQuery(name= "product.updateProductById",query = "update Product p set p.name = :name,p.dtPrice = :price where p.id = :id")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(nullable = false,
	columnDefinition = "varchar(45) check(char_length(name)>=4)")
	private String name;
	@Column(name ="dt_price",columnDefinition = "int default 0")
	private int dtPrice;
	@Column(name="ws_price")
	private int wsPrice;
	
	@ManyToOne
	private Category category;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
}





