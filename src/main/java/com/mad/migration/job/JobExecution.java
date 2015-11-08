package com.mad.migration.job;

import java.util.Date;

public class JobExecution {
	
	private volatile Date startTime = null;

	private volatile Date createTime = new Date(System.currentTimeMillis());

	private volatile Date endTime = null;
	
	private volatile JobStatus jobStatus = JobStatus.UNKNOW;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public String toString() {
		return "JobExecution [startTime=" + startTime + ", createTime=" + createTime + ", endTime=" + endTime
				+ ", jobStatus=" + jobStatus + "]";
	}
	
	
	
}

