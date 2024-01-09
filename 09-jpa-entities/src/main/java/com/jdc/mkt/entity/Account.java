package com.jdc.mkt.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.jdc.mkt.listener.EnableTimesListener;
import com.jdc.mkt.listener.Times;
import com.jdc.mkt.listener.TimesListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account_tbl")
@NoArgsConstructor
@RequiredArgsConstructor
//@EntityListeners(TimesListener.class)
//@ExcludeDefaultListeners
public class Account implements EnableTimesListener{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String name;
	@NonNull
	private Integer balance;
	private Times times;

//	@PrePersist
//	void beforePersist() {
//		System.out.println("Before persist");
//	}
//	@PreUpdate
//	void beforeMerge() {
//		System.out.println("Before update");
//	}
//	@PreRemove
//	void beforeRemove() {
//		System.out.println("Before remove");
//	}
//	
//	@PostLoad
//	void afterFind() {
//		System.out.println("After find");
//	}
	
}
