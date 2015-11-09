package com.mad.migration.baseline;

import org.springframework.beans.factory.annotation.Autowired;

import com.mad.migration.annotation.MadJob;
import com.mad.migration.job.MadSimpleJob;
import com.mad.migration.job.item.ItemReader;

@MadJob(name="baseline", enable=true)
public class BaselineJob extends MadSimpleJob {
	
	
	@Autowired
	private BaselineItemReader baselineItemReader;
	
	@Override
	public ItemReader getReader() {
		return baselineItemReader;
	}

	
	
}
