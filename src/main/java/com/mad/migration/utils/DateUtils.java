package com.mad.migration.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static final String dd_M_yyy_hh_mm_s ="dd-M-yyyy hh:mm:s";
	
	public static final String EEE_MMM_dd_yyyy_hh_mm_aaa ="EEE MMM dd yyyy hh:mm aaa";
	

	
	public static Date toDate(String strDate, String pattern) throws ParseException {
	
		DateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(strDate);
	}
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
		
	
}
