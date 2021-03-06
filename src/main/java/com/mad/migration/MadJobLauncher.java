package com.mad.migration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.mad.migration.annotation.MadJob;
import com.mad.migration.job.Job;
import com.mad.migration.job.JobLauncher;


@Component
public class MadJobLauncher implements BeanPostProcessor,JobLauncher {
	
	
	private Logger logger = LoggerFactory.getLogger(MadJobLauncher.class);
	
	private List<Job> jobs = new ArrayList<>();
	
	@Value("${mad.enable.venrdors}")
	private List<String> enableVendors;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Autowired
	private ApplicationEventMulticaster applicationEventMulticaster;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean.getClass().isAnnotationPresent(MadJob.class)) {
			MadJob madJob = bean.getClass().getAnnotation(MadJob.class);
			
			if(enableVendors.contains(madJob.name())) {
				//set job name
				Job job = (Job)bean;
				job.setJobName(madJob.name());
				jobs.add(job);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	
	public void launcher() throws Exception{
		for(Job job: jobs) {
			taskExecutor.execute(new Runnable() {				
				@Override
				public void run() {					
					logger.info("Job: {} launched" , job.getJobName());	
					try {
						job.execute();
					}catch(Exception ex) {
						logger.error("Job {} running with error {}",job.getJobName(),ex);	
					}
					logger.info("Job: {} completed with execution {} ", job.getJobName(), job.getLastExecution());					
					//fire event to verify each job					
				}
			});			
		}		
	}



}
