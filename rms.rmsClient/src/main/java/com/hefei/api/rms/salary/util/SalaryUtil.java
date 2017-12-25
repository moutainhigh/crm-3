package com.hefei.api.rms.salary.util;

import java.util.HashMap;
import java.util.Map;

public class SalaryUtil {

	/** 个人养老保险缴费比率 */
	private static final Double PERSONAL_RATE_YANGLAO = 0.08;
	/** 个人医疗保险缴费比率 */
	private static final Double PERSONAL_RATE_YILIAO = 0.02;
	/** 个人失业保险缴费比率 */
	private static final Double PERSONAL_RATE_SHIYE = 0.005;
	/** 个人公积金缴费比率 */
	private static final Double PERSONAL_RATE_GONGJIJIN = 0.12;
	/** 个税起征点 */
	private static final int THRESHOLD_POINT = 3500;
	
	
	/**
	 * 计算个人所得税
	 * @param salary 工资
	 * @param fund 三险一金（不包括工伤保险和生育保险）
	 * @param threshold 起征点
	 * @param rate 税率
	 * @param netNumber 速算扣除数
	 * 
	 * @return 个人所得税计算公式：个税=[(工资-三险一金-起征点)*税率]-速算扣除数
	 */
	public static Double getTax(Double salary){
		
		Double taxSalary = salary-salary*(PERSONAL_RATE_YANGLAO+PERSONAL_RATE_YILIAO+PERSONAL_RATE_SHIYE+PERSONAL_RATE_GONGJIJIN) - THRESHOLD_POINT;
		Map<String, Object> map = getReatAndNetnumber(taxSalary);
		Double rate = (Double) map.get("rate");
		Integer netNumber = (Integer) map.get("netNumber");
		Double tax =  taxSalary * rate - netNumber;
		return tax;
	}
	
	/**
	 * 根据工资获取对应等级税率和速算扣除数
	 * @param salary
	 * @return
	 */
	public static Map<String, Object> getReatAndNetnumber(Double taxSalary){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(taxSalary <= 1500){
			map.put("rate", 0.03);
			map.put("netNumber", 0);
		}
		if(taxSalary > 1500 && taxSalary <= 4500){
			map.put("rate", 0.10);
			map.put("netNumber", 105);
		}
		if(taxSalary > 4500 && taxSalary <= 9000){
			map.put("rate", 0.20);
			map.put("netNumber", 555);
		}
		if(taxSalary > 9000 && taxSalary <= 35000){
			map.put("rate", 0.25);
			map.put("netNumber", 1005);
		}
		if(taxSalary > 35000 && taxSalary <= 55000){
			map.put("rate", 0.30);
			map.put("netNumber", 2755);
		}
		if(taxSalary > 55000 && taxSalary <= 80000){
			map.put("rate", 0.35);
			map.put("netNumber", 5505);
		}
		if(taxSalary > 80000){
			map.put("rate", 0.45);
			map.put("netNumber", 13505);
		}
		
		return map;
		
	}
	
	public static Double getGongJiJin(Double salary){
		return salary * PERSONAL_RATE_GONGJIJIN;
	}
}
