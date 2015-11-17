package com.mad.migration.tms;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.job.JdbcReader;
import com.mad.migration.job.MadItemReader;


@Component
@Lazy(value=true)
public class TmsItemReader extends MadItemReader{

	@Autowired
	private TmsMovieReader movieReader;
	
	@Override
	public List<JdbcReader<MadItemData>> initialingReaders() {
		
		List<JdbcReader<MadItemData>>list = new ArrayList<>();
		list.add(movieReader);
	
		return list;
	}
	
	
	
}
