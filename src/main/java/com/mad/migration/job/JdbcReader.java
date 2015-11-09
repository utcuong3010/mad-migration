package com.mad.migration.job;

import org.springframework.dao.DataAccessException;

public interface JdbcReader<T> {
	
	public T read() throws DataAccessException;

}
