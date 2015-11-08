package com.mad.migration.job.item;


/**
 * process item
 * @author utcuong3010
 *
 * @param <I>
 * @param <O>
 */
public interface ItemProcessor<I,O> {
	
	O process(I item) throws Exception;
}
