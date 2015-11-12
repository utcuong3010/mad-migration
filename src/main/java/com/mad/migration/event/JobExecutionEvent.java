package com.mad.migration.event;

import com.mad.migration.job.JobExecution;

public class JobExecutionEvent extends BaseEvent{

	
	private JobExecution jobExecution;
	private int processedItems;
	public JobExecutionEvent(Object source, String jobName, int totalItem,int processedItems, JobExecution jobExecution) {
		super(source,jobName,totalItem);		
		this.jobExecution = jobExecution;
		this.processedItems = processedItems;
	}
	
	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public int getProcessedItem() {

		return this.processedItems;
	}
}
