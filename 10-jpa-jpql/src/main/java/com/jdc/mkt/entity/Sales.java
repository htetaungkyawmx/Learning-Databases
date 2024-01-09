package com.jdc.mkt.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name ="sale_date",
	columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime saleDate;
	@ManyToOne
	private Customer customer;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
}
