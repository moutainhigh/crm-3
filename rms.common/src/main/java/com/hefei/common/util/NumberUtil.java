package com.hefei.common.util;


public class NumberUtil {

	/**
	 * 是否能够被100整除 0/NULL返回false
	 */
	public static boolean isNumberBy100(int num){
		if(num == 0)
			return false;
		int yu = num%100;
		if(yu == 0)
			return true;
		return false;
	}
	
}
