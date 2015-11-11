package com.mad.migration.verify;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.JobInfo;
import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.VerificationInfo;
import com.mad.migration.job.JobStatus;
import com.mad.migration.utils.DateUtils;
import com.mad.migration.utils.FileUtils;

@Component
@Scope("prototype")
public class MadSimpleVerifyJob implements VerifyJob,ApplicationContextAware {
	
	
	
	private Logger logger = LoggerFactory.getLogger(MadSimpleVerifyJob.class);

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;		
	}
	

	private CsvItemReader csvItemReader;
	
	@Autowired
	private VerifyItemProcessor verifyItemProcessor;
	
	
	
	@Value("${mad.migration.homeDir}")
	private String homeDirectory;
	
	@Override
	public void doVerify(JobInfo jobInfo) throws Exception {
		
		boolean isContinue = true;
		boolean isSucceed = false;
		VerificationInfo verifyInfo = new VerificationInfo();
		try {
			//set data
			verifyInfo.setName(jobInfo.getJobName());
			verifyInfo.setStartDate(new Date());
			verifyInfo.setTotal(jobInfo.getTotalItems());
			String dataPath = homeDirectory + File.separator + "read-data" + File.separator + jobInfo.getJobName() + ".csv";
			//init for each job
			csvItemReader = applicationContext.getBean(CsvItemReader.class, dataPath);
			
			while(isContinue) {
				
				MadItemData item = csvItemReader.read();
				if(item == null) break;
				//check
				isSucceed = verifyItemProcessor.process(item);
		
				if(isSucceed) {
					verifyInfo.setPassedItems(verifyInfo.getPassedItems() + 1);
					
				} else {
					verifyInfo.setFailedItems(verifyInfo.getFailedItems() + 1);
				}
				
			}
			
			verifyInfo.setEndDate(new Date());
			verifyInfo.setStatus(JobStatus.COMPLETED);
			
		} catch(Exception exception) {
			
			logger.error("Verify job {} failed with exception {}", jobInfo, exception);
			verifyInfo.setEndDate(new Date());
			verifyInfo.setStatus(JobStatus.ERROR);
		}
		
		//persistence the verify
		doPersist(verifyInfo);
		
	}
	
	
	private void doPersist(VerificationInfo info) {

		String reportPath = homeDirectory + File.separator + "verify-data" + File.separator + info.getName() + "_verification.txt";
//		// write into log
		StringBuilder contentReportBuilder = new StringBuilder("\n");
		contentReportBuilder.append("******************************[" + info.getName() +"]-Verify Report*****************************\n");
		contentReportBuilder.append("Name:" + info.getName() + "\n");
		contentReportBuilder.append("Total:" + info.getTotal() + "\n");
		contentReportBuilder.append("Passed Items:" + info.getPassedItems() + "\n");
		contentReportBuilder.append("Failed Items:" + info.getFailedItems() + "\n");
		contentReportBuilder.append("Status:" + info.getStatus() + "\n");
		contentReportBuilder.append("Started Date:" + DateUtils.formatDate(info.getStartDate(),DateUtils.dd_M_yyy_hh_mm_s)  + "\n");
		contentReportBuilder.append("Ended Date:" + DateUtils.formatDate(info.getEndDate(),DateUtils.dd_M_yyy_hh_mm_s)  + "\n");
		
		logger.info(contentReportBuilder.toString());				
		FileUtils.writeLog(reportPath,contentReportBuilder.toString());
	}
	
}
