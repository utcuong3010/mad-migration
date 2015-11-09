package com.mad.migration.job.item;

public interface ItemReader<T> {
	
	T read() throws Exception;
	int count() throws Exception;
}

