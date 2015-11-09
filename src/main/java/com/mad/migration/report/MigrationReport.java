package com.mad.migration.report;

import java.io.File;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MigrationInfo;
import com.mad.migration.event.ItemErrorEvent;
import com.mad.migration.event.JobExecutionEvent;
import com.mad.migration.utils.FileUtils;

@Component
public class MigrationReport implements Report,ApplicationListener<ApplicationEvent>{

	
	@Value("${mad.migration.homeDir}")
	private String homeDirectory;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final ThreadLocal<MigrationInfo> threadLocal = new ThreadLocal<>();
	
	
	@Override
	public void doReport() {
		
		MigrationInfo info = threadLocal.get();
		String reportPath = homeDirectory + File.separator + "report-data" + File.separator + info.getName() + "_migration.txt";
		
		FileUtils.deleteFile(reportPath);
	
		// write into log
		StringBuilder contentReportBuilder = new StringBuilder("\n");
		contentReportBuilder.append("******************************STARTED Migration Report*****************************\n");
		contentReportBuilder.append("Name:" + info.getName() + "\n");
		contentReportBuilder.append("Total:" + info.getTotal() + "\n");
		contentReportBuilder.append("Migrated Items:" + (info.getTotal() - info.getFailedItems()) + "\n");
		contentReportBuilder.append("Failed Items:" + info.getFailedItems() + "\n");
		contentReportBuilder.append("Status:" + info.getStatus() + "\n");
		contentReportBuilder.append("Started Date:" + new SimpleDateFormat("dd-MM-yyy hh:mm").format(info.getStartDate())  + "\n");
		contentReportBuilder.append("Ended Date:" + new SimpleDateFormat("dd-MM-yyy hh:mm").format(info.getEndDate())  + "\n");
		contentReportBuilder.append("******************************ENDED Migration Report*****************************\n");
		
		logger.info(contentReportBuilder.toString());
				
		FileUtils.writeLog(reportPath,contentReportBuilder.toString());
		
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		MigrationInfo info = threadLocal.get();
		if(info == null) {
			info = new MigrationInfo();
		}
		if(event instanceof ItemErrorEvent) {
			
			info.setFailedItems(info.getFailedItems() + 1);
			threadLocal.set(info);
		} else if(event instanceof JobExecutionEvent) {
			JobExecutionEvent jobEvent = (JobExecutionEvent)event;
			info.setName(jobEvent.getJobName());
			info.setTotal(jobEvent.getTotalItems());
			info.setStartDate(jobEvent.getJobExecution().getStartTime());
			info.setStatus(jobEvent.getJobExecution().getJobStatus());
			info.setEndDate(jobEvent.getJobExecution().getEndTime());
			threadLocal.set(info);
			//call do report
			doReport();
		}
		
	}
}
