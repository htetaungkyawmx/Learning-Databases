package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
@Table(name = "customer_tbl")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String name;
	@OneToOne
	//@JoinTable(name = "cu_address_tbl")
	@MapsId
	@PrimaryKeyJoinColumn
	private Address address;
	@ManyToMany
	private List<Product> products;
	
	public void addAddress(Address address) {
		//this.id = address.getId();
		address.setCustomer(this);
		this.address = address;
	}
	
}











