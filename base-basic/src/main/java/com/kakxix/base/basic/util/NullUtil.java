/*
 * Copyright 2017 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

/**
 * <P><P>
 * @author caojiayao 
 * @version $Id: NullUtil.java, v 0.1 2018年4月19日 下午6:29:34 caojiayao Exp $
 */
public final class NullUtil {

    /**
     * <P> 非空执行valCall <P>
     * @param val
     * @param valCall
     * @return
     */
    public static <T, V> V callValOrNull(T val, ValCall<T, V> valCall) {
        return val != null ? valCall.call(val) : null;
    }

    /**
     * <P>内容获得<P>
     * @author caojiayao 
     * @version $Id: NullUtil.java, v 0.1 2018年4月19日 下午8:08:37 caojiayao Exp $
     */
    public static interface ValCall<T, V> {
        public V call(T t);
    }

}
