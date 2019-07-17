package com.kakxix.base.basic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import com.kakxix.base.basic.enums.BaseEnum;
import com.kakxix.base.basic.util.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <P> 日志监控埋点注解 <P>
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMonitor {

    /**IN_PARAMETER:从入参获取字段,OUT_PARAMETER:从出参获取字段**/
    ParamType paramType() default ParamType.IN_PARAMETER;

    /** 参数中的reqNo 的字段名 **/
    String reqNoKey() default "reqNo";

    /** 业务类型 **/
    LogBizType logBizType();

    /** 服务类型 **/
    ServiceType serviceType() default ServiceType.API;

    /** 请求成功失败的字段名 **/
    String successKey() default "respCode";

    /** 请求成功值 **/
    String successVal() default "0000";

    /** 参数中的msg 的字段名 **/
    String msgKey() default "respMsg";

    /** 参数中的amount 的字段名 **/
    String amountKey() default "amount";

    /** 参数中的partnerNo 的字段名 **/
    String partnerNoKey() default "productCode";

    /**
     * <P> 参数类型 <P>
     * @author caojiayao 
     * @version $Id: LogMonitor.java, v 0.1 2018年5月11日 上午11:50:25 caojiayao Exp $
     */
    @Getter
    @AllArgsConstructor
    public static enum ParamType implements BaseEnum {
        OUT_PARAMETER("OUT_PARAMETER", "出参类型"),
        IN_PARAMETER("IN_PARAMETER", "入参类型");

        /** code **/
        private final String code;

        /** message **/
        private final String message;

        /**
         * <P><P>
         * @return
         */
        @Override
        public String toString() {
            return getCode();
        }

        /**
         * <P> <P>
         */
        public static ParamType get(String code) {
            return EnumUtil.valueOf(ParamType.class, code);
        }
    }

    /**
     * <P> 日志业务状态 <P>
     * @author caojiayao 
     * @version $Id: LogMonitor.java, v 0.1 2018年5月11日 上午11:51:31 caojiayao Exp $
     */
    @Getter
    @AllArgsConstructor
    public static enum LogBizStatus implements BaseEnum {
        SUCCESS("1", "成功"),
        FAIL("2", "失败");

        /** code **/
        private final String code;

        /** message **/
        private final String message;

        /**
         * <P><P>
         * @return
         */
        @Override
        public String toString() {
            return getCode();
        }

        /**
         * <P> <P>
         */
        public static LogBizStatus get(String code) {
            return EnumUtil.valueOf(LogBizStatus.class, code);
        }
    }

    /**
     * <P> 业务日志类型 <P>
     */
    @Getter
    @AllArgsConstructor
    public static enum LogBizType implements BaseEnum {

        // 账务操作
        KEEP_ACCOUNTS_SYNC("KEEP_ACCOUNTS_SYNC", "账务记账"),
        QUERY_LEDGER_ACCT("QUERY_LEDGER_ACCT", "查询账务帐号"),
        QUERY_LEDGER_ACCT_TXN("QUERY_LEDGER_ACCT_TXN", "查询账务记账"),

        ;
        /** code **/
        private final String code;

        /** message **/
        private final String message;

        /**
         * <P><P>
         * @return
         */
        @Override
        public String toString() {
            return getCode();
        }

        /**
         * <P> <P>
         */
        public static LogBizType get(String code) {
            return EnumUtil.valueOf(LogBizType.class, code);
        }
    }

    /**
     * <P> 服务类型 <P>
     */
    @Getter
    @AllArgsConstructor
    public static enum ServiceType implements BaseEnum {
        API("API", "api"),
        SERVICE("SERVICE", "application service"),
        DOMAINSERVICE("DOMAINSERVICE", "domain service"),
        REMOTE("REMOTE", "remote"),
        TASK("TASK", "task"),
        ONS("ONS", "ons"),

        ;
        /** code **/
        private final String code;

        /** message **/
        private final String message;

        /**
         * <P><P>
         * @return
         */
        @Override
        public String toString() {
            return getCode();
        }

        /**
         * <P> <P>
         */
        public static ServiceType get(String code) {
            return EnumUtil.valueOf(ServiceType.class, code);
        }
    }
}
