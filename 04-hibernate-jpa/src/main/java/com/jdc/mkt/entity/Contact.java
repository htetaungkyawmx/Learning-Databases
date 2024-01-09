package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String primaryContact;
	private String secondaryContact;
	
	
	
	
}
