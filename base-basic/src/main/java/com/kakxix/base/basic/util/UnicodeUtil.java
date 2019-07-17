/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import lombok.experimental.UtilityClass;

/**
 * 类UnicodeUtil.java的实现描述：Unicode工具类
 * 
 * @author caimin 2017年7月20日 下午4:38:47
 */
@UtilityClass
public class UnicodeUtil {

    /**
     * string转unicode
     * 
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static String string2Unicode(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); //取出高8位 
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位 
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);

        }
        return (new String(sb));
    }

    /**
     * unicode转String
     * 
     * @param str 待转字符串
     * @return 普通字符串
     */
    public static String unicode2String(String str) {
        str = (str == null ? "" : str);
        if (str.indexOf("\\u") == -1)//如果不是unicode码则原样返回 
        {
            return str;
        }

        StringBuffer sb = new StringBuffer(1000);

        for (int i = 0; i < str.length() - 6;) {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++) {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar) {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }

                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }
}
