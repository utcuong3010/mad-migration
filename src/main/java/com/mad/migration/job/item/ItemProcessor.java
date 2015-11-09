package com.mad.migration.job.item;

import com.mad.migration.exception.BusinessException;
import com.mad.migration.exception.ErrorException;

/**
 * process item
 * @author utcuong3010
 *
 * @param <I>
 * @param <O>
 */
public interface ItemProcessor<I,O> {
	
	O process(I item) throws BusinessException,ErrorException;
}
