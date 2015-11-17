package com.mad.migration.verify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;

@Component
@Scope("prototype")
@Lazy(value=true)
public class CsvItemReader implements InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(CsvItemReader.class);
	
	private String csvFile;
	
	private Queue<MadItemData> data ;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	private AtomicBoolean initialized = new AtomicBoolean(false);
	
	
	public CsvItemReader(String csvFile) {
		this.csvFile = csvFile;
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		data = new ConcurrentLinkedQueue<>();
		//start worker here
		taskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				String line = "";
				String cvsSplitBy = ",";
				BufferedReader br = null;
				try {

					br = new BufferedReader(new FileReader(csvFile));
					while ((line = br.readLine()) != null) {
					        // use comma as separator
						String[] itemData = line.split(cvsSplitBy);
						MadItemData item = new MadItemData();
						try {
							item = item.recoveryData(itemData);
						} catch (ParseException e) {						
							logger.error("read file {} with error {}", csvFile,e);
						}
						data.add(item);//add item
						
						initialized.set(true);

					}

				} catch (IOException e) {
					logger.error("Reading csv {} with error",csvFile,e);
					initialized.set(true);
					
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							logger.error("closing csv {} with error",csvFile,e);
						}
					}
				}
				
			}
		});
		
	}
	
	public MadItemData read() throws Exception {
		//waiting object initialized
		while (!initialized.get()) {
			Thread.sleep(1000);
		}
		return data.poll();		
	}
	
}
