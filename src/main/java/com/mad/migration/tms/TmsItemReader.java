package com.mad.migration.tms;	

import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.job.item.ItemReader;

@Component
public class TmsItemReader implements ItemReader<MadItemData> {

	
	private int count = 0;
	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub
		if(++count <10) {
		
			System.err.println("tms read item" + count);
			Thread.sleep(30);
			MadItemData item = new MadItemData();
			item.setVendor(new Vendor("TMS", "TMS", "TMS_thumbnail"));
//			if(count == 6) throw new Exception("dal");
		
			return item;
		} else {
			return null;
		}
	}

	
}
