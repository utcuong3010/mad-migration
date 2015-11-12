package com.mad.migration.event;

public class ItemErrorEvent extends BaseEvent{

	
	private Object item;
	private Exception exception;
	
	
	public ItemErrorEvent(Object source, String jobName,int totalItems,Object item,Exception exception) {
		super(source,jobName,totalItems);		
		this.item = item;
		this.exception = exception;
	}
	
	public Exception getException() {
		return exception;
	}
	
	public Object getItem() {
		return item;
	}
	
}

