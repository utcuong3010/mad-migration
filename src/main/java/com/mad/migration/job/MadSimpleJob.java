package com.mad.migration.job;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ApplicationEventMulticaster;

import com.mad.migration.event.ItemErrorEvent;
import com.mad.migration.event.ItemReadEvent;
import com.mad.migration.event.JobExecutionEvent;
import com.mad.migration.exception.BusinessException;
import com.mad.migration.job.item.ItemProcessor;
import com.mad.migration.job.item.ItemReader;
import com.mad.migration.job.item.ItemWriter;

public abstract class MadSimpleJob implements Job{
	
	
	private Logger logger = LoggerFactory.getLogger(MadSimpleJob.class);
	

	private String jobName;
	
	@Value("${mad.migration.chunkSize:5}")
	private int chunkSize;
	
	
	@Autowired
	private ApplicationEventMulticaster applicationEventMulticaster;

	private volatile JobExecution jobExecution = new JobExecution();
	@Override
	public JobExecution getLastExecution() {

		return jobExecution;
	}
	
	@Autowired
	private ItemProcessor itemProcessor;
	public ItemProcessor getProcessor() {
		return itemProcessor;
	}
	
	@Autowired
	private ItemWriter itemWriter;
	public ItemWriter getWriter() {
		return itemWriter;
	}
	
	@Override
	public void setJobName(String jobName) {
		this.jobName = jobName;		
	}
	@Override
	public String getJobName() {
		return jobName;
	}
	
	/***
	 *  we must override for specific each vendor 
	 */
	public abstract ItemReader getReader();
	
	

	
	public void execute() throws Exception {
		//read
		jobExecution = new JobExecution();
		jobExecution.setJobStatus(JobStatus.RUNNING);
		jobExecution.setStartTime(new Date());
		boolean isContinue = true;
		Object inItem = null;
		Object outItem = null;
		List<Object> items = new ArrayList<>();
		int totalItems = 0;
		try {	
			//get total items
			totalItems = getReader().count();
					
			//do execute		
			while(isContinue) {
				inItem = getReader().read();
				if(inItem != null) {
					applicationEventMulticaster.multicastEvent(new ItemReadEvent(this,jobName,totalItems,inItem));//publish event read				
					try {
						outItem = getProcessor().process(inItem);//process item			
					}catch(BusinessException  ex) {
						applicationEventMulticaster.multicastEvent(new ItemErrorEvent(this, jobName,totalItems, inItem, ex));
					}
				} else {
					//stop
					isContinue = false;
				}
				//save in the chunk when chuck-size is reached please commit the transaction unit
				if(outItem != null) {
					items.add(outItem);
					if(items.size() == chunkSize) {
						getWriter().write(items);//write them
						//reset 
						items = new ArrayList<>();
					}
				} else if(items.size() > 0) {
					getWriter().write(items);//write them
				}					
				//repeat again
				inItem = null;
				outItem = null;
			}
			
			jobExecution.setEndTime(new Date());
			jobExecution.setJobStatus(JobStatus.COMPLETED);
			applicationEventMulticaster.multicastEvent(new JobExecutionEvent(this, jobName,totalItems,jobExecution));
			
		} catch (Exception ex) {		
						
			jobExecution.setEndTime(new Date());
			jobExecution.setJobStatus(JobStatus.ERROR);
			applicationEventMulticaster.multicastEvent(new JobExecutionEvent(this, jobName,totalItems,jobExecution));
			
			//commit writer
			if(items.size() > 0) {
				getWriter().write(items);//write them
			}	
			throw ex;
		}
		
	}
	
}
