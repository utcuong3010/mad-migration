package com.mad.migration.job;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.job.item.ItemReader;

public abstract class MadItemReader implements ItemReader<MadItemData>,InitializingBean{
	
	private Logger logger = LoggerFactory.getLogger(MadItemReader.class);
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	private Queue<MadItemData>queue = new ConcurrentLinkedQueue<>();
	
	
	private List<JdbcReader<MadItemData>> readers;
	
	private int MAX_WAITING = 5;

	@Override
	public MadItemData read() throws Exception {
		//waiting initialized
		int waiting = 0;
		while(queue.size() == 0) {
			Thread.sleep(1000);
			waiting ++;
			if(waiting == MAX_WAITING) break;
		}		
		//TODO: read next 
		return queue.poll();
	}
	
	@Override
	public int count() throws Exception {
		int totalItems = 0;
		for(JdbcReader reader : readers) {
			totalItems +=  reader.totalItems();
		}
		
		logger.info("couted total items {} for reader {}", totalItems, this.getClass());
		return totalItems;
	}
	
	/**
	 * have to initializing readers
	 * @return
	 */
	public abstract List<JdbcReader<MadItemData>> initialingReaders() ;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		//initializing reader 
		readers = initialingReaders();		
		taskExecutor.execute(new Runnable() {			
			@Override
			public void run() {
				for(JdbcReader reader: readers) {				
					while(true) {
						List<MadItemData> items = reader.read();	
						if(items != null && items.size() > 0) {
							queue.addAll(items);
						} else {
							break;
						}
					}
				}
			}
		});
	}

}
