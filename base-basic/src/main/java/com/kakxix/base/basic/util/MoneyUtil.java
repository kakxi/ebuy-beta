package com.kakxix.base.basic.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 类MoneyUtil.java的实现描述：TODO 类实现描述
 * 
 * @author xiay 2018年6月19日 下午4:39:51
 */
public class MoneyUtil {

    public static String add(String v1, String v2) {
        if (StringUtils.isEmpty(v1)) {
            v1 = "0";
        }
        if (StringUtils.isEmpty(v2)) {
            v2 = "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    public static String subtract(String v1, String v2) {
        if (StringUtils.isEmpty(v1)) {
            v1 = "0";
        }
        if (StringUtils.isEmpty(v2)) {
            v2 = "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }

    public static String multiply(Long v1, Long v2) {
        if (v1 == null || v2 == null) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    public static String divide(String v1, String v2) {
        return divide(v1, v2, 2);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 除数
     * @param v2 被除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String divide(String v1, String v2, Integer scale) {
        if (StringUtils.isEmpty(v1) || StringUtils.isEmpty(v2) || StringUtils.equals(v1, "0")) {
            return "0";
        }
        if (scale == null) {
            scale = 2;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }
}
