package com.mad.migration.event;

import org.springframework.context.ApplicationEvent;

public class ItemReadEvent extends ApplicationEvent {
	
	private String jobName;
	private Object item;
	
	public ItemReadEvent(Object source,String jobName, Object item ) {
		super(source);
		this.item = item;
		this.jobName = jobName;
	}

	public Object getItem() {
		return item;
	}
	
	public String getJobName() {
		return jobName;
	}
}
