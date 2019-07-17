/*
 * @author caojiayao 2017年4月20日 下午4:09:45
 */
package com.kakxix.base.basic.util;

import java.util.UUID;

/**
 * UUID 唯一标识
 */
public class UUIDUtil {
	
	public static String gen() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
        System.out.println(UUIDUtil.gen());
    }

}
