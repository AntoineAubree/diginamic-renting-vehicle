package fr.diginamic.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateUtils {

	/**
	 * 
	 * @return 
	 */
	public static LocalDate getDate(String stringDate) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
		return LocalDate.parse(stringDate, formatter);
	}
	
	public static String getDateInString(LocalDate localDate) {
		String stringDate = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
		return stringDate;
	}

}
