package com.aitrich.smsapi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {

	public static final int OFFSET_DAYS = 1;

	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(
			"dd/MM/yyyy");

	public static Date convertStringToDate(String stringDate) {

		try {
			return DATE_FORMATTER.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static String convertDatetoString(Date date) {
		return DATE_FORMATTER.format(date);
	}

	public static Date removeTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateWithoutTime;
	}

	public static String getAmPmFromDateTime(Date dateTime) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(dateTime);
		if (calender.get(Calendar.AM_PM) == 0) {
			return "AM";
		} else {
			return "PM";
		}
	}

	/*
	 * public static Date getForecastDate(Date startDate, int offset) { Calendar
	 * date = Calendar.getInstance(); date.setTime(startDate);
	 * date.add(Calendar.DATE, offset); return date.getTime(); }
	 * 
	 * public static Date getSettlementDate(Date forecastSettlementDate, int
	 * ppl) {
	 * 
	 * //Add standard offset Date settlementDate =
	 * getForecastDate(forecastSettlementDate, OFFSET_DAYS); int actualPPL = ppl
	 * + 1;
	 * 
	 * for (int i = 0; i < 7; i++) { Date pplDate =
	 * getForecastDate(settlementDate,i); Calendar calendar =
	 * Calendar.getInstance(); calendar.setTime(pplDate);
	 * 
	 * if (calendar.get(Calendar.DAY_OF_WEEK) == actualPPL) { settlementDate =
	 * pplDate; break; } }
	 * 
	 * return settlementDate; }
	 */

	public static Date getNextWorkingDate(Date actualDate) {
		Calendar date = Calendar.getInstance();
		// date.setTime(getForecastDate(actualDate,1));

		boolean isSaturday = isSaturday(date);
		boolean isSunday = isSunday(date);

		if (isSaturday || isSunday) {
			// date.setTime(getForecastDate(date.getTime(),1));
		}

		isSaturday = isSaturday(date);
		isSunday = isSunday(date);

		if (isSaturday || isSunday) {
			// date.setTime(getForecastDate(date.getTime(),1));
		}

		return date.getTime();
	}

	private static boolean isSaturday(Calendar calDate) {
		return calDate.get(Calendar.DAY_OF_WEEK) == 7;
	}

	private static boolean isSunday(Calendar calDate) {
		return calDate.get(Calendar.DAY_OF_WEEK) == 1;
	}
}
