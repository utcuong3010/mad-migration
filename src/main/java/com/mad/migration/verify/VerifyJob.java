package com.mad.migration.verify;

import com.mad.migration.domain.JobInfo;

public interface VerifyJob {
	void doVerify(JobInfo jobInfo) throws Exception;
}
