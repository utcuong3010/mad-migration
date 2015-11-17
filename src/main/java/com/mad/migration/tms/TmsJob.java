package com.mad.migration.tms;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mad.migration.annotation.MadJob;
import com.mad.migration.job.MadSimpleJob;
import com.mad.migration.job.item.ItemReader;

@MadJob(name="TMS")
public class TmsJob extends MadSimpleJob implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;
		
	}

	@Override
	public ItemReader getReader() {

		return applicationContext.getBean(TmsItemReader.class);//init bean when use
	}

	
	
}
