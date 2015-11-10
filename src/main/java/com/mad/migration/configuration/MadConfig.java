package com.mad.migration.configuration;

import java.io.File;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.mad.migration.utils.FileUtils;

@Component
public class MadConfig implements InitializingBean{

	@Value("${mad.migration.homeDir}")
	private String homeDirectory;
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
		threadPoolExecutor.setCorePoolSize(10);
		threadPoolExecutor.setMaxPoolSize(20);
		threadPoolExecutor.setQueueCapacity(100);	
//		threadPoolExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
		threadPoolExecutor.setAllowCoreThreadTimeOut(true);
//		threadPoolExecutor.setDaemon(true);
		threadPoolExecutor.initialize();
		return threadPoolExecutor;
	}
	
	
	@Bean
	public ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
		applicationEventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());		
		return applicationEventMulticaster;
	}
	
	public void madInitializer() {
		
		
		FileUtils.deleteFolder(homeDirectory);
		//remove home directory
		//create read-data
		FileUtils.createDirectory(homeDirectory + File.separator + "read-data");
		FileUtils.createDirectory(homeDirectory + File.separator + "error-data");
		FileUtils.createDirectory(homeDirectory + File.separator + "report-data");
		FileUtils.createDirectory(homeDirectory + File.separator + "verify-data");
		
		
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		
		madInitializer();
		
	}
	
	
}
