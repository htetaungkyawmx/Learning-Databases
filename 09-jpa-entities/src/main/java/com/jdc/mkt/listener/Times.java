package com.jdc.mkt.listener;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class Times implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}
