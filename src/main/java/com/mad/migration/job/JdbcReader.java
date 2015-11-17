package com.mad.migration.job;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface JdbcReader<T> {
	
	public List<T> read() throws DataAccessException;
	public int totalItems() throws DataAccessException;

}
