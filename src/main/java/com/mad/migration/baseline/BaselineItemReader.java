package com.mad.migration.baseline;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.directv.apg.mad.general.domain.SourceProgramType;
import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.job.item.ItemReader;

@Component(value="baselineItemReader")
public class BaselineItemReader implements ItemReader<MadItemData> {

	private int totalItems = 10000;
	private int count = 0;

	
	@Override
	public MadItemData read() throws Exception {
		// TODO Auto-generated method stub
		if(++count<=totalItems) {
//			System.err.println("Baseline read item");
			
			MadItemData item = new MadItemData();
			
			item.setProgramId("134242");
			item.setRootId("11");
			item.setProgramType(SourceProgramType.SHOW);
			item.setCreatedDate(new Date());
			
			
			
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
