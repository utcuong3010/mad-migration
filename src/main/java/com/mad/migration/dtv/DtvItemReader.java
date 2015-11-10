package com.mad.migration.dtv;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.job.item.ItemReader;
import com.mad.migration.temp.SourceProgramType;

@Component
public class DtvItemReader implements ItemReader<MadItemData> {

	private int totalItems = 100;
	private int count = 0;
	
	public DtvItemReader() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub
		if(++count<=totalItems) {
//			System.err.println("DTV read item");
			MadItemData item = new MadItemData();
			
			item.setProgramId("134242");
			item.setRootId("11");
			item.setProgramType(SourceProgramType.SHOW);
			item.setCreatedDate(new Date());
		
			item.setVendor(new Vendor("dtv", "DTV", "DTV_thumbnail"));
			
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
