package fr.diginamic.utils;

import java.util.regex.Pattern;

public class RegexUtils {

	/**
	 * 
	 * @return
	 */
	public static boolean isPositiveFloat(String string) {
		return Pattern.matches("^([+]?\\d*\\.?\\d*)$", string);
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isFloat(String string) {
		return Pattern.matches("^([+-]?\\d*\\.?\\d*)$", string);
	}

	/**
	 * 
	 * @return 
	 */
	public static boolean containsOnlyIntegerOrUppercaseLetter(String string) {
		return Pattern.matches("^[A-Z0-9]*$", string);
	}

}
