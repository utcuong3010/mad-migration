package com.mad.migration.job;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;

import com.mad.migration.event.ItemReadEvent;
import com.mad.migration.job.item.ItemProcessor;
import com.mad.migration.job.item.ItemReader;
import com.mad.migration.job.item.ItemWriter;

public abstract class MadSimpleJob implements Job{
	
	
	private Logger logger = LoggerFactory.getLogger(MadSimpleJob.class);
	
	private final int chunkSize = 2;
	
	
	@Autowired
	private ApplicationEventMulticaster applicationEventMulticaster;

	
	
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
	
	
	/***
	 *  we must override for specific each vendor 
	 */
	public abstract ItemReader getReader();
	
	

	
	public void execute() {
		try {		
			//read
			boolean isContinue = true;
			Object inItem = null;
			Object outItem = null;
			List<Object> items = new ArrayList<>();
			while(isContinue) {
				inItem = getReader().read();
				if(inItem != null) {
					applicationEventMulticaster.multicastEvent(new ItemReadEvent(inItem, new Date()));//publish event read										
					outItem = getProcessor().process(inItem);//process item									
				} else {
					//stop
					isContinue = false;
				}
				//save in the chunk when chucksize is reached please commit the transaction unit
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
			
		} catch (Exception ex) {		
			logger.error("Job {} with error {}", getName(),ex);			
		}
	}
	
}
