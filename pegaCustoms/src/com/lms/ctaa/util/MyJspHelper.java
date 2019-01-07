package com.lms.ctaa.util;

import java.util.regex.Pattern;

public class MyJspHelper {

	public static boolean matches(String str, String level) {
//		String pattern = "^(([^-\\s]+-{1}){" + level + "}[^-\\s]+)$";
		String pattern = "^(([^\\\\{2}\\s]+\\\\{1}){" + level + "}[^\\\\{2}\\s]+)$";
        return Pattern.compile(pattern).matcher(str).matches();
      }
}
