package com.mad.migration.baseline;

import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.job.item.ItemReader;

@Component
public class BaselineItemReader implements ItemReader<MadItemData> {

	private int totalItems = 3;
	private int count = 0;
	
	public BaselineItemReader() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub
		if(++count<totalItems) {
			System.err.println("Baseline read item");
			
			MadItemData item = new MadItemData();
			item.setVendor(new Vendor("Baseline", "Baseline", "Baseline_thumbnail"));
			
			return item;
		} else {
			return null;
		}
	}

	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return totalItems;
	}
}
