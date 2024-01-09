package com.jdc.mkt.entity;

import java.awt.Image;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@IdClass(ProductPk.class)
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(generator = "product_gen")
//	@SequenceGenerator(name = "product_gen", allocationSize = 1)
//	//@TableGenerator(name = "product_gen")
//	private int id;
	
//	@EmbeddedId
//	private ProductPk id;
	@Id
	private int categoryId;
	@Id
	private int supplierId;
	
	@NonNull
	@Column(columnDefinition = "varchar(45) check(char_length(name) >=6 )",nullable = false)
	private String name;
	@NonNull
	private Integer price;
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(45) not null default 'SMALL'")
	private Size size;
//	@Temporal(TemporalType.TIME)
//	private Date date;
//	private LocalDate lcDate;
//	private LocalTime lcTime;
//	private LocalDateTime lcDatetime;
	
	@Transient
	private boolean active;
	@Lob
	private Image image;
	
	public enum Size{
		SMALL,MEDIUM,LARGE
	}
}
