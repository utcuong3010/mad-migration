package com.mad.migration.event;

import org.springframework.context.ApplicationEvent;

public class ItemReadEvent extends ApplicationEvent {
	
	private Object item;
	
	public ItemReadEvent(Object source, Object item ) {
		super(source);
		this.item = item;
	}

	public Object getItem() {
		return item;
	}
}
