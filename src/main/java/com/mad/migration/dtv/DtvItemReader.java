package com.mad.migration.dtv;

import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.job.item.ItemReader;

@Component
public class DtvItemReader implements ItemReader<MadItemData> {

	private int totalItems = 10;
	private int count = 0;
	
	public DtvItemReader() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub
		if(++count<totalItems) {
			System.err.println("DTV read item");
			
			MadItemData item = new MadItemData();
			item.setVendor(new Vendor("DTV", "DTV", "DTV_thumbnail"));
			
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
