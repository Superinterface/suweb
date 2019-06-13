package com.sujianan.util;

/**
 * 判断对象是否为空, true表示为空, false表示不为空.
 * @author	github: SuperInteface
 * @date	2019年6月13日
 */
public class Empty {
	
	public static boolean isEmptyByte(Byte b) {
		return b == null ? true : false;
	}
	
	public static boolean isEmptyShort(Short s) {
		return s == null ? true : false;
	}
	
	public static boolean isEmptyInteger(Integer i) {
		return i == null ? true : false;
	}
	
	public static boolean isEmptyLong(Long l) {
		return l == null ? true : false;
	}
	
	public static boolean isEmptyFloat(Float f) {
		return f == null ? true : false;
	}
	
	public static boolean isEmptyDouble(Double d) {
		return d == null ? true : false;
	}
	
	public static boolean isEmptyCharacter(Character c) {
		return c == null ? true : c == '\0' ? true : false;
	}
	
	public static boolean isEmptyBoolean(Boolean b) {
		return b == null ? true : false;
	}
	
	public static boolean isEmptyString(String s) {
		return ( s == null || "".equals(s) ) ? true : false;
	}
	
	public static boolean isEmptyObject(Object o) {
		return false;
	}

}
