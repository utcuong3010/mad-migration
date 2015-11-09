package com.mad.migration.domain;

import java.util.Date;

import com.mad.migration.job.JobStatus;

public class MigrationInfo {
		
	private String name;
	private JobStatus status;
	private int total;
	private int migratedItems;
	private int failedItems;
	private Date startDate;
	private Date endDate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getMigratedItems() {
		return migratedItems;
	}
	public void setMigratedItems(int migratedItems) {
		this.migratedItems = migratedItems;
	}
	public int getFailedItems() {
		return failedItems;
	}
	public void setFailedItems(int failedItems) {
		this.failedItems = failedItems;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	

}
