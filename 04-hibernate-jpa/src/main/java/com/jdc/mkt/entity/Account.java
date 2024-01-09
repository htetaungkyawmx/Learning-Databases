package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.GenerationType.TABLE;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity
@Getter
@Setter
@Table(name = "account_tbl",uniqueConstraints = {
		@UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "password")
},indexes = {
		@Index(columnList = "name")
})
//@SecondaryTables({
//	@SecondaryTable(name = "address_tbl"),
//	@SecondaryTable(name ="contact_tbl")
//})
@SecondaryTable(name = "address_tbl")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "account_gen")
	@TableGenerator(name = "account_gen", allocationSize = 1, initialValue = 1)
	private int id;
	private String name;
	private String loginId;
	private String password;
	private Contact contact;
	
	@Column(table = "address_tbl")
	private String street;
	@Column(table = "address_tbl")
	private String township;
	@Column(table = "address_tbl")
	private String city;
	
	
	
	
	
	
	
	
}
