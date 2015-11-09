package com.mad.migration.exception;

/**
 * define some error like as Read error,Call webservice error, null pointer ...
 * @author cuongtv8
 *
 */
public class ErrorException extends Exception{

	public ErrorException(String msg) {
		
		super(msg);
	}
	
}
