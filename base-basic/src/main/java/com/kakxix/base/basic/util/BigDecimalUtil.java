/*
 * **********************************************************
 *
 * 原点系统
 *
 * ©2019 深圳瀚德法信区块链科技有限公司 保留所有权利
 *
 * **********************************************************
 */
package com.kakxix.base.basic.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

/**
 * @Class BigDecimalUtil
 * @Author Linyuning
 * @Date 2019/3/19 11:50
 */
@Slf4j
public class BigDecimalUtil {

    private BigDecimalUtil(){}

    public static int compare(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) return 0;
        if (a == null) return -1;
        if (b == null) return 1;
        return a.compareTo(b);
    }

    public static BigDecimal valueOf(String value, BigDecimal defaultValue) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return defaultValue;
        }
    }

    public static boolean lessThan(BigDecimal a, BigDecimal b) {
        return compare(a, b) < 0;
    }

    public static boolean moreThan(BigDecimal a, BigDecimal b) {
        return compare(a, b) > 0;
    }

    public static boolean lessThanZero(BigDecimal a) {
        return lessThan(a, BigDecimal.ZERO);
    }

    public static boolean moreThanZero(BigDecimal a) {
        return moreThan(a, BigDecimal.ZERO);
    }

    public static boolean equals(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    private interface BigDecimalOperator extends BinaryOperator<BigDecimal> {}

    private static BigDecimal operation(BigDecimal a, BigDecimal b, BigDecimalOperator operator) {
        if (a == null && b == null) {
            return new BigDecimal(0);
        }
        if (a == null) return b;
        if (b == null) return a;
        return operator.apply(a, b);
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return operation(a, b, (c, d) -> c.add(d));
    }

    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        return operation(a, b, (c, d) -> c.subtract(d));
    }

    public static BigDecimal mul(BigDecimal a, BigDecimal b) {
        return operation(a, b, (c, d) -> c.multiply(d));
    }

    public static BigDecimal div(BigDecimal a, BigDecimal b) {
        return operation(a, b, (c, d) -> c.divide(d));
    }
}
