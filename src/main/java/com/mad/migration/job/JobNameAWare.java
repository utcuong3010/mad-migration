package com.mad.migration.job;

import org.springframework.beans.factory.Aware;

public interface JobNameAWare extends Aware {
	void setJobName(String jobName);
}
