package com.jdc.mkt.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class Contact {

	@Column(nullable = false,
			columnDefinition = "varchar(45) check(email like '%@%')")
	private String email;
	@Column(nullable = false,unique = true)
	private String phone;
}
