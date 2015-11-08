package com.mad.migration.dtv;

import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.job.item.ItemReader;

@Component
public class DtvItemReader implements ItemReader<MadItemData> {

	
	public DtvItemReader() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub

		System.err.println("dtv read item");
		Thread.sleep(3000);

		
		return new MadItemData();
	}
}
