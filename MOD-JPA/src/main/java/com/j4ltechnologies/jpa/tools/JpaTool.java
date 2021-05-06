package com.j4ltechnologies.jpa.tools;

import java.time.LocalDate;
import java.time.Period;

public class JpaTool {
	
	public static boolean isNullOrWithSpace(String str) {
		return str == null || str.trim().isEmpty();
	};

	public static String capitalize(String str) {
		if(!isNullOrWithSpace(str)) {
			return str.toUpperCase().substring(0, 1) + str.toLowerCase().substring(1);
		} else {
			return null;
		}
		
	}
	
	public static String allCapitalize(String str) {
		return str.trim().toUpperCase();
	}

	public static int calculeAge(LocalDate birthday) {
		LocalDate now = LocalDate.now();
		return Period.between(birthday, now).getYears();
	}

}
