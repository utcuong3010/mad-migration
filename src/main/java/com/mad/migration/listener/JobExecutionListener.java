package com.mad.migration.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.JobInfo;
import com.mad.migration.event.JobExecutionEvent;
import com.mad.migration.job.JobExecution;
import com.mad.migration.verify.VerifyJob;

@Component
public class JobExecutionListener implements ApplicationListener<JobExecutionEvent>,ApplicationContextAware{

	
	private Logger logger = LoggerFactory.getLogger(JobExecution.class);
	
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	

	@Override
	public void onApplicationEvent(JobExecutionEvent jobExecutionEvent) {
		
		JobInfo jobInfo = new JobInfo();
		try {			
			jobInfo.setJobName(jobExecutionEvent.getJobName());
			//look up in the context
			VerifyJob verifyJob = applicationContext.getBean(VerifyJob.class);
			System.err.println("trigger......." + verifyJob);
			
			verifyJob.doVerify(jobInfo);
			logger.info("Verify job {} done",jobInfo);
		} catch (Exception exception) {
			logger.info("Verify job {} done with error {}",jobInfo, exception);
		}

	}

}
