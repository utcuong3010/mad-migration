package com.mad.migration.tms;

import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.job.item.ItemReader;

@Component
public class TmsItemReader implements ItemReader<MadItemData> {

	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub
		
		
		System.err.println("tms read item");
		Thread.sleep(3000);

		
		return new MadItemData();
	}

	
}