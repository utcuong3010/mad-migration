package com.mad.migration.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mad.migration.event.JobExecutionEvent;

@Component
public class JobExecutionListener implements ApplicationListener<JobExecutionEvent> {

	@Override
	public void onApplicationEvent(JobExecutionEvent jobExecutionEvent) {
		// TODO Auto-generated method stub
		System.err.println("job execution event" + jobExecutionEvent.getJobExecution());
		
	}
	
	
	

}
