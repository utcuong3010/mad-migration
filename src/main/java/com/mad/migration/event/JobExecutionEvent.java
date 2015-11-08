package com.mad.migration.event;

import org.springframework.context.ApplicationEvent;

import com.mad.migration.job.JobExecution;

public class JobExecutionEvent extends ApplicationEvent{

	private String jobName;
	private JobExecution jobExecution;
	public JobExecutionEvent(Object source, String jobName, JobExecution jobExecution) {
		super(source);
		this.jobName = jobName;
		this.jobExecution = jobExecution;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	public JobExecution getJobExecution() {
		return jobExecution;
	}
}
