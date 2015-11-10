package com.mad.migration.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static final String MM_DD_YY_HH_MM ="MM/dd/yyyy hh:mm";
	
	public static Date toDate(String strDate, String pattern) throws ParseException {
	
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(strDate);
	}
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
		
	
}
