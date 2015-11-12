package com.mad.migration.event;

import org.springframework.context.ApplicationEvent;

public abstract class BaseEvent extends ApplicationEvent{
	
	
	private String jobName;
	private int totalItems;
	

	public BaseEvent(Object source, String jobName,int totalItems) {
		super(source);
		this.jobName =  jobName;
		this.totalItems =  totalItems;
	
	}

	public int getTotalItems() {
		return totalItems;
	}
	
	public String getJobName() {
		return jobName;
	}
		
	
}
