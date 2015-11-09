package com.mad.migration.event;

import org.springframework.context.ApplicationEvent;

public class ItemReadEvent extends BaseEvent {
	
	private Object item;
	
	public ItemReadEvent(Object source,String jobName,int totalItem, Object item ) {
		super(source,jobName,totalItem);
		this.item = item;
		
	}

	public Object getItem() {
		return item;
	}
	
}
