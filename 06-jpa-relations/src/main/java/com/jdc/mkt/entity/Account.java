package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private String value1;
	private String value2;
	@ElementCollection
	private List<String> tags;
}
