package com.mad.migration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MadConfiguration {

	
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
	
}
