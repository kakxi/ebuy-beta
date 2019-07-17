/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.kakxix.base.basic.enums.MessageCodeEnum;
import com.kakxix.base.basic.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static java.beans.Introspector.getBeanInfo;

/**
 * <p>
 * <p>
 *
 * @author caojiayao
 * @version $Id: BeanUtil.java, v 0.1 2018年5月11日 上午11:41:35 caojiayao Exp $
 */
@Slf4j
public class BeanUtil {

    private static final String CLASS_KEY = "class";

    /**
     * <p>
     * 是否可以bean -> map
     * <p>
     *
     * @param bean
     * @return
     */
    public static boolean isBeanToMap(Object bean) {
        return bean != null && !ClassUtils.isPrimitiveOrWrapper(bean.getClass()) && bean.getClass() != BigDecimal.class
                && !bean.getClass().isEnum() && bean.getClass() != Date.class && bean.getClass() != String.class;
    }

    /**
     * JavaBean转换为Map
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object bean) {

        Map<String, Object> map = Maps.newHashMap();
        try {
            //获取所有的属性描述器
            final PropertyDescriptor[] pds = getBeanInfo(bean.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                if (CLASS_KEY.equals(pd.getName())) {
                    continue;
                }
                map.put(pd.getName(), pd.getReadMethod().invoke(bean));
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return map;
    }


    /**
     * JavaBean转换为Map
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, String> beanToMapStr(Object bean) {

        Map<String, String> map = Maps.newHashMap();
        try {
            //获取所有的属性描述器
            final PropertyDescriptor[] pds = getBeanInfo(bean.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                if (CLASS_KEY.equals(pd.getName())) {
                    continue;
                }

                final Object value = pd.getReadMethod().invoke(bean);
                map.put(pd.getName(), value != null ? value.toString() : null);
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return map;
    }

    /**
     * 转换对象为目标类型（destType）
     * @param destType 目标类型
     * @param orig 原对象
     * @return <code>T</code>
     * @exception <code>SystemException</code
     * 
     * @author Linyuning
     * @date 2019/2/25
     */
    public static <T> T transferProperties(final Class<T> destType, final Object orig) throws SystemException {
        try {
            if (destType == null) return null;
            T dest = destType.newInstance();
            if (orig == null) return dest;
            BeanUtils.copyProperties(dest, orig);
            return dest;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw new SystemException(MessageCodeEnum.SERVERERROR);
        }
    }


    /**
     * 转换对象为目标类型（destType）
     * @param destType 目标类型
     * @param orig 原对象
     * @return <code>T</code>
     * @exception <code>SystemException</code
     *
     * @author Linyuning
     * @date 2019/2/25
     */
    public static <T> List<T> transferProperties(final Class<T> destType, final List<?> orig) throws SystemException {
        if (destType == null || orig == null) return Collections.emptyList();
        return orig.stream().map(e -> transferProperties(destType, e)).collect(Collectors.toList());
    }


    /**
     * 根据字段名复制对象
     * @param dest
     * @param orig
     * @param propNames 需要复制的字段名
     * @return <code>boolean</code> true：更新，false：未更新
     *
     * @author Linyuning
     * @date 2019/4/8
     */
    public static boolean copyProperties(Object dest, Object orig, List<String> propNames) {
        return copyProperties(dest, orig, propNames, (a, b) -> b != null && !Objects.equals(a, b));
    }

    /**
     * 根据字段名或条件复制对象
     * @param dest
     * @param orig
     * @param propNames 需要复制的字段名
     * @param predicate 不传或true : 复制，false：不复制
     * @return <code>boolean</code> true：更新，false：未更新
     *
     * @author Linyuning
     * @date 2019/4/8
     */
    public static boolean copyProperties(Object dest, Object orig, List<String> propNames, BiPredicate<Object, Object> predicate) {
        boolean updated = false;
        boolean checkPropNames = CollectionUtils.isNotEmpty(propNames);
        PropertyUtilsBean propertyUtilsBean = BeanUtilsBean.getInstance().getPropertyUtils();
        final PropertyDescriptor[] origDescriptors = propertyUtilsBean.getPropertyDescriptors(orig);
        for (PropertyDescriptor origDescriptor : origDescriptors) {
            final String name = origDescriptor.getName();
            if (CLASS_KEY.equals(name)) {
                continue;
            }
            if (checkPropNames && !propNames.contains(name)) {
                continue;
            }
            if (propertyUtilsBean.isReadable(orig, name) && propertyUtilsBean.isWriteable(dest, name)) {
                try {
                    final Object destValue = propertyUtilsBean.getSimpleProperty(dest, name);
                    final Object origValue = propertyUtilsBean.getSimpleProperty(orig, name);
                    if (predicate != null && !predicate.test(destValue, origValue)) {
                        continue;
                    }
                    BeanUtilsBean.getInstance().copyProperty(dest, name, origValue);
                    updated = true;
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    log.warn(e.getMessage());
                }
            }
        }
        return updated;
    }

}
