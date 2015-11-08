package com.mad.migration.configuration;

import java.io.File;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.mad.migration.utils.FileUtils;

@Component
public class MadConfiguration implements InitializingBean{

	@Value("${mad.migration.homeDir}")
	private String homeDirectory;
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
		threadPoolExecutor.setCorePoolSize(10);
		threadPoolExecutor.setMaxPoolSize(20);
		threadPoolExecutor.setQueueCapacity(0);		
		threadPoolExecutor.initialize();
		return threadPoolExecutor;
	}
	
	@Bean
	public ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
		applicationEventMulticaster.setTaskExecutor(taskExecutor());		
		return applicationEventMulticaster;
	}
	
	public void madInitializer() {
		//remove home directory
		FileUtils.createDirectory(homeDirectory);
		//create read-data
		FileUtils.createDirectory(homeDirectory + File.separator + "read-data");
		
		
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		
		madInitializer();
		
	}
	
	
}
