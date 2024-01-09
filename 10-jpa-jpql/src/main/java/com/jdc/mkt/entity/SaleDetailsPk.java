package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailsPk implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "product_id")
	private int productId;
	@Column(name = "sales_id")
	private int salesId;
}
