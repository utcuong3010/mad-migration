package com.mad.migration.tms;

import org.springframework.beans.factory.annotation.Autowired;

import com.mad.migration.annotation.MadJob;
import com.mad.migration.job.MadSimpleJob;
import com.mad.migration.job.item.ItemReader;

@MadJob(name="TMS",enable=true)
public class TmsJob extends MadSimpleJob {
	
	@Autowired
	private TmsItemReader tmsItemReader;

	@Override
	public String getName() {
		return "TMSVendor";
	}

	@Override
	public ItemReader getReader() {

		return tmsItemReader;
	}

	
	
}