package com.hefei.common.util;

import java.math.BigDecimal;

/**
 * 金额计算工具类。
 */
public class MoneyUtil {
    
    private MoneyUtil(){}
    
    /*
     * 比较
     */
    public static int compare(Number num1,Number num2){
    	BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.compareTo(bd2);
    }
    
    /*
     * 加法
     */
    public static Double add(Number num1,Number num2){
        BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.add(bd2).doubleValue();
    }

    /*
     * 减法
     */
    public static Double subtract(Number num1,Number num2){
        BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.subtract(bd2).doubleValue();
    }
    
    
    /*
     * 减法[]
     */
    public static BigDecimal subtractInBigDecimal(Number num1,Number num2){
        BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.subtract(bd2);
    }
    
    
    /*
     * 乘法
     */
    public static Double multiply(Number num1,Number num2){
    	BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.multiply(bd2).doubleValue();
    }
    
    /*
     * 除法
     */
    public static Double divide(Number num1,Number num2){
    	BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.divide(bd2, 10, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
    public static Double min(Number num1,Number num2){
    	BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.compareTo(bd2) < 0 ? bd1.doubleValue() : bd2.doubleValue();
    }
    
    public static Double max(Number num1,Number num2){
    	BigDecimal bd1=new BigDecimal(num1==null?"0":num1.toString());
        BigDecimal bd2=new BigDecimal(num2==null?"0":num2.toString());
        return bd1.compareTo(bd2) > 0 ? bd1.doubleValue() : bd2.doubleValue();
    }
    
    public static double  round(Number num){
    	double amount =  new BigDecimal(num+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    	return amount;
    }
    
    public static double  roundUp(Number num){
    	double amount =  new BigDecimal(num+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    	return amount;
    }
    public static double  roundDown(Number num){
    	double amount =  new BigDecimal(num+"").setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    	return amount;
    }
}
