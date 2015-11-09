package com.mad.migration.domain;

import java.util.Date;

import com.mad.migration.job.JobStatus;

public class VerifyInfo {
	
	private String name;
	private JobStatus status;
	private int total;
	private int passedItems;
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
	public int getPassedItems() {
		return passedItems;
	}
	public void setPassedItems(int passedItems) {
		this.passedItems = passedItems;
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
	@Override
	public String toString() {
		return "VerifyInfo [name=" + name + ", status=" + status + ", total=" + total + ", passedItems=" + passedItems
				+ ", failedItems=" + failedItems + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	
	
}
