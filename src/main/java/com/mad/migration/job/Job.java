package com.mad.migration.job;

/***
 * 
 * @author utcuong3010
 *
 */
public interface Job extends JobNameAware{
	
	String getJobName();
	void execute() throws Exception;
	JobExecution getLastExecution();

}