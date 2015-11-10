package com.mad.migration.job;

import org.springframework.beans.factory.Aware;

public interface JobNameAware extends Aware {
	void setJobName(String jobName);
}
