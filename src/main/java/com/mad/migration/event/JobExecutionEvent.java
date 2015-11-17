package com.mad.migration.event;

import com.mad.migration.job.JobExecution;

public class JobExecutionEvent extends BaseEvent{

	
	private JobExecution jobExecution;
	private int processedItems;
	private int failedItems;
	public JobExecutionEvent(Object source, String jobName, int totalItem,int processedItems,int failedItems, JobExecution jobExecution) {
		super(source,jobName,totalItem);		
		this.jobExecution = jobExecution;
		this.processedItems = processedItems;
		
		this.failedItems=failedItems;
	}
	
	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public int getProcessedItem() {

		return this.processedItems;
	}
}
