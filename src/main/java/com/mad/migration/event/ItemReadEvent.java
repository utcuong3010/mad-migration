package com.mad.migration.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class ItemReadEvent extends ApplicationEvent {
	
	private Date time;
	
	
	public ItemReadEvent(Object source, Date time) {
		super(source);
		this.time = time;
	}
	
	
	public Date getTime() {
		return time;
	}
	
	

	
	
}
