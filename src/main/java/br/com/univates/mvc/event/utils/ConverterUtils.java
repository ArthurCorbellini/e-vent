package br.com.univates.mvc.event.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Arthur
 */
public class ConverterUtils {

	public static String calendarConverter(Calendar calendar, String format) {
		return new SimpleDateFormat(format == null ? "dd/MM/YYYY - HH:mm" : format).format(calendar);
	}

	public static String calendarConverter(Calendar calendar) {
		return calendarConverter(calendar, null);
	}

}
