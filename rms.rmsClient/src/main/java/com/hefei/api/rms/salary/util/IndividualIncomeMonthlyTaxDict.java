package com.hefei.api.rms.salary.util;

import java.util.ArrayList;
import java.util.List;

import com.hefei.common.util.MoneyUtil;

/**
 * 个税
 */
public class IndividualIncomeMonthlyTaxDict {

	private static List<IndividualIncomeTaxRange> taxList = new ArrayList<IndividualIncomeTaxRange>();
	
	private static IndividualIncomeTaxRange l1 = new IndividualIncomeTaxRange(0, 1500, 0.03d,0);
	private static IndividualIncomeTaxRange l2 = new IndividualIncomeTaxRange(1500, 4500, 0.1d,105);
	private static IndividualIncomeTaxRange l3 = new IndividualIncomeTaxRange(4500, 9000, 0.2d,555);
	private static IndividualIncomeTaxRange l4 = new IndividualIncomeTaxRange(9000, 35000, 0.25d,1005);
	private static IndividualIncomeTaxRange l5 = new IndividualIncomeTaxRange(35000, 55000, 0.3d,2755);
	private static IndividualIncomeTaxRange l6 = new IndividualIncomeTaxRange(55000, 80000, 0.35d,5505);
	private static IndividualIncomeTaxRange l7 = new IndividualIncomeTaxRange(80000, -1d, 0.45d,13505);
	
	static{
		taxList.add(l1);
		taxList.add(l2);
		taxList.add(l3);
		taxList.add(l4);
		taxList.add(l5);
		taxList.add(l6);
		taxList.add(l7);
	}

	public static IndividualIncomeTaxRange get(double amount){
		for(IndividualIncomeTaxRange range : taxList){
			if(range.getMin() < amount && range.getMax() > amount){
				return range;
			}
		}
		return l7;
	}
	
	public static double calTax(double amount){
		IndividualIncomeTaxRange range = get(amount);
		double tax = MoneyUtil.multiply(amount, range.getRate());
		tax = MoneyUtil.subtract(tax, range.getDeduct());
		return tax;
	}
}
