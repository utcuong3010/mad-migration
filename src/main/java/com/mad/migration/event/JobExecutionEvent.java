package com.mad.migration.event;

import com.mad.migration.job.JobExecution;

public class JobExecutionEvent extends BaseEvent{

	
	private JobExecution jobExecution;
	public JobExecutionEvent(Object source, String jobName, int totalItem, JobExecution jobExecution) {
		super(source,jobName,totalItem);		
		this.jobExecution = jobExecution;
	}
	
	public JobExecution getJobExecution() {
		return jobExecution;
	}
}
