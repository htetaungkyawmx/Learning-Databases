package com.jdc.mkt.entity;

import java.awt.Color;
import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ProductPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int categoryId;
	private int supplierId;
	
}
