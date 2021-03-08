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

	/**
	 * 
	 * @return
	 */
	public static boolean containsOnlyLetterOrMinusCharactereOrSpace(String string) {
		return Pattern.matches("^[A-Za-z\s-]*$", string);
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isIntegerBetwenOneAndNine(char c) {
		return Pattern.matches("^[1-9]*$", Character.toString(c));
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isInteger(char c) {
		return Pattern.matches("^[0-9]*$", Character.toString(c));
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isInteger(String string) {
		return Pattern.matches("^[0-9]*$", string);
	}

}
