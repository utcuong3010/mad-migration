package com.mad.migration.listener;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mad.migration.event.ItemErrorEvent;
import com.mad.migration.utils.FileUtils;

@Component
public class ItemErrorListener implements ApplicationListener<ItemErrorEvent>{
	
	@Value("${mad.migration.homeDir}")
	private String homeDirectory;

	@Override
	public void onApplicationEvent(ItemErrorEvent event) {
		// write into log
//		if(event.getException() instanceof BusinessException) {
		FileUtils.writeLog(homeDirectory + File.separator + "error-data" + File.separator + event.getJobName() + ".csv",
						event.getItem().toString());
//		}
		
	}
}
