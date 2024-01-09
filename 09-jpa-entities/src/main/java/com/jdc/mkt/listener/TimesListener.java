package com.jdc.mkt.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class TimesListener {

	@PrePersist
	void createTimes(Object obj) {
		if(obj instanceof EnableTimesListener entity) {
			var times = entity.getTimes();
			if(null == times) {
				times = new Times();
				times.setCreateTime(LocalDateTime.now());
			}
			entity.setTimes(times);
		}
	}
	
	@PreUpdate
	void updateTimes(Object obj) {
		if(obj instanceof EnableTimesListener entity) {
			var times = entity.getTimes();
			if(null == times) {
				times = new Times();
				times.setUpdateTime(LocalDateTime.now());
			}
			entity.setTimes(times);
		}
	}
}
