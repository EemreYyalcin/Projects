package com.woo.utils.converter;

import com.woo.core.attributes.Codes;

public class ConverterType {

	public static int toInt(String value) {
		try {
			return Integer.valueOf(value);
		}
		catch (Exception e) {
			e.printStackTrace();
			return Codes.errorIntCode;
		}
	}

}
