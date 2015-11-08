package com.mad.migration.dtv;

import org.springframework.beans.factory.annotation.Autowired;

import com.mad.migration.annotation.MadJob;
import com.mad.migration.job.MadSimpleJob;
import com.mad.migration.job.item.ItemReader;

@MadJob(name="DTVVendor", enable=true)
public class DtvJob extends MadSimpleJob {
	
	
	@Autowired
	private DtvItemReader dtvItemReader;

	@Override
	public String getName() {
		return "Dtv Vendor";
	}

	@Override
	public ItemReader getReader() {
		return dtvItemReader;
	}

	
	
}
