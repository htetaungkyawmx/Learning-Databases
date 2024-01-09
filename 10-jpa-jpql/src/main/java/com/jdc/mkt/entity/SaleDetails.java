package com.jdc.mkt.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name =  "sale_details")
@Entity
public class SaleDetails {

	@EmbeddedId
	private SaleDetailsPk id;
	private int qty;
	private int total;
	@MapsId("salesId")
	@ManyToOne
	private Sales sales;
	@MapsId("productId")
	@ManyToOne
	private Product product;
}
