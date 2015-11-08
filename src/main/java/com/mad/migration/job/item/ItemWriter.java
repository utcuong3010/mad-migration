package com.mad.migration.job.item;

import java.util.List;

/**
 * 
 * @author utcuong3010
 *
 * @param <T>
 */
public interface ItemWriter<T> {

	void write(List<? extends T> items) throws Exception;
	
}
