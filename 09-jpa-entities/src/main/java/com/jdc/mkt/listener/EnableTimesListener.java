package com.jdc.mkt.listener;

import java.io.Serializable;

public interface EnableTimesListener extends Serializable{

	void setTimes(Times time);
	Times getTimes();
}
