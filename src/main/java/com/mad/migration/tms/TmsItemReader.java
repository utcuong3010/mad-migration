package com.mad.migration.tms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.job.item.ItemReader;


@Component
public class TmsItemReader implements ItemReader<MadItemData>{

	@Autowired
	private TmsMovieReader movieReader;

	@Override
	public MadItemData read() throws Exception {
		MadItemData itemData = movieReader.read();
		//TODO: read next 
		return itemData;
	}
	
	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return 100;
	}
	
	
}
