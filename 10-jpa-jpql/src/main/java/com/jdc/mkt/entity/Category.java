package com.jdc.mkt.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(nullable = false)
	private String name;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
	@OneToMany(mappedBy = "category")
	private List<Product> products;
}
