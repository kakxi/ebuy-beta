/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类MessageCodeEnum.java的实现描述：错误编码枚举
 * 
 * @author lijianglong 2018年3月23日 下午5:14:00
 */
@Getter
@AllArgsConstructor
public enum MessageCodeEnum implements BaseEnum {

    /**
     * 执行成功
     */
    SUCCESSCODE("1", "执行成功"),

    /**
     * 参数错误
     */
    PARAMERROR("0001", "参数错误"),

    /**
     * 业务错误
     */
    BISERROR("0002", "业务错误"),

    /**
     * 找不到相关数据
     */
    DATA_NOT_FOUND("0004", "找不到相关数据"),

    /**
     * TOKEN不允许为空
     */
    TOKEN_NOT_NULL("9997", "您未登录"),

    /**
     * TOKEN失效
     */
    TOKENERROR("9998", "您与原点已断开连接"),

    /**
     * 服务错误
     */
    SERVERERROR("9999", "服务错误"),

    /**
     * 格式错误
     */
    FORMAT_ERROR("1000", "格式错误"),

    /**
     * 服务入口,以1开始
     */
    SIGN_VALIDATE_FAIL("1001", "签名验证失败"),
    PARAM_DECRYPT_FAIL("1002", "参数解密失败"),
    NO_SERVICEID_FIND_ERROR("1003", "未查询到对应的服务配置或配置信息错误"),
    NOT_LATEST_VERSION_ERROR("1005", "请及时更新最新版本"),

    /*
     * 个人中心以2开始
     */
    USER_QUERY_RETURN_NULL("2001", "查询用户信息返回为空"),

    INVITER_INVALID("2002", "邀请人信息非法"),

    INVITEE_REACHED_MAX_NUMBER("2003", "被邀请人已达最大人数"),

    MOBILE_LOGIN_VERIFYCODE_NULL("2004", "手机号登陆,短信验证码为空"),

    MOBILE_VERIFYCODE_FAIL("2005", "短信验证码验证失败"),

    LOGIN_FAIL("2006", "登陆失败"),

    NOT_SIGNUP_USER_ERROR("2007", "该手机号码未曾注册"),

    ALREADY_REAL_NAME_AUTH_ERROR("2008", "实名认证已完成"),

    USER_BIZ_ACCOUNT_NOT_FOUND_ERROR("2010", "用户业务子账户不存在"),

    REAL_NAME_AUTH_FAIL("2011", "实名认证失败"),

    USER_OPEN_FAIL("2012", "用户系统开户失败"),

    CHANNEL_ACCOUNT_OPEN_FAIL("2013", "账户系统渠道开户失败"),

    BIZ_ACCOUNT_OPEN_FAIL("2014", "账户系统业务开户失败"),

    INSIDE_TEST_CODE_EEROR("2015", "内测码错误"),

    SIGNUP_USER_ERROR("2016", "注册失败"),

    ALREADY_SIGNUP_USER_ERROR("2017", "该手机号码已被注册"),

    WECHAT_ALREADY_SIGNUP_USER_ERROR("2036", "该微信号已被绑定至其它账号,绑定失败"),

    CERTI_NO_ALREADY_AUTHORIZED_ERROR("2018", "该身份证已被认证"),

    CONDISION_NOT_ALLOWED("2019", "暂不符合申请条件"),

    CERTID_EXPIRED_ERROR("2020", "该身份证已过期"),

    CERTID_SCAN_ERROR("2021", "身份证识别错误，请重新拍摄"),

    FACE_RECOGNIZE_FAIL("2022", "人脸识别失败"),

    OCR_CHECK_FAIL("2023", "身份证识别失败"),

    OTP_INTERVAL_TOO_SHORT_ERROR("2024", "距离上次验证码发送时间太近了"),

    OTP_CHECK_FAIL("2025", "验证码输入错误"),

    OTP_SENT_FAIL("2026", "验证码发送失败，请1分钟后再次尝试"),

    OTP_SENT_ERROR("2027", "验证码发送失败，请再次获取"),

    ACTIVITY_REWARD_STRATEGY_ERROR("2028", "活动奖励策略错误"),

    ACTIVITY_COIN_ISSUE_ERROR("2029", "活动原石奖励发放失败"),

    ACTIVITY_REWARD_ISSUE_ERROR("2030", "活动奖励奖品发放失败"),

    ACTIVITY_REWARD_EMPTY_ERROR("2031", "活动奖励奖品已发放完"),

    MSG_SEND_FAIL("2027", "消息发送失败"),

    MSG_STATUS_UPDATE_FAIL("2032", "消息状态更新失败"),

    WECHAT_CHECK_FAIL("2033", "微信登录验证失败"),

    WECHAT_NOT_SIGNUP_USER_ERROR("2034", "该微信帐号未曾注册"),

    WECHAT_CONFIG_ERROR("2035", "微信登录配置错误"),
    NOT_IN_THE_GUESSING("2037", "非竞猜中活动,不允许申请"),

    USER_EDIT_FAIL("2038", "用户系统编辑用户信息失败"),

    CHANNEL_INFO_ERROR("2039", "渠道信息错误"),

    CHANNEL_CODE_ERROR("2040", "渠道编码错误"),

    SEND_OTP_LIMIT("2041", "验证码发送超限"),

    FACE_RECOGNITION_FAILURE_OVERRUN("2042", "识别失败，请明天再次尝试"),

    CERTINO_DISCERN_FAILURE_OVERRUN("2043", "身份证号输入错误，今天无法再次尝试"),

    MODIFY_PAY_PASSWROD_CONDITION("2044", "修改密码前需要先进行人脸识别或身份证认证成功"),

    MOBILE_SUCCEED_VERIFY_HAVE_EXPIRED("2045", "短信未成功验证或成功验证已过1天有效期"),

    PASS_VERIFY_ERROR("2046", "密码验证错误"),
    
    PASS_LOGIN_NOT_EXIST("2047", "登录密码不允许为空"),

    MODIFY_PASSWROD_DEFEATED("2048", "密码设置或修改失败"),

    NICK_NAME_CAN_NOT_BE_EMPTY("2049", "请输入昵称"),

    NICK_NAME_ALREADY_EXISTS("2050", "该昵称已存在"),

    NICKNAME_SET_ALREADY("2051", "已设置过昵称"),

    THE_TASK_IS_COMPLETE("2052", "任务已完成"),

    THE_TASK_IS_OFFLINE("2053", "该任务已下线"),

    THE_TASK_IS_NOT_APPLY_OR_TIMEOUT("2054", "操作超时，请5分钟内完成操作"),

    THE_TASK_APPLY_DEAL_FAIL("2055", "申请处理失败,请再次尝试 "),

    THE_TASK_APPLY_DEAL_PROCESSING("2056", "任务申请处理中，请稍候重试"),

    THE_TASK_CALL_BACK_ILLEGAL("2057", "任务完成请求非法"),

    THE_TASK_PRIZES_IN_PROGRESS("2058", "任务发奖中，请勿重复申请"),

    THE_REDEEM_ORDER_REPEAT_APPLICATION("2059", "兑换订单重复申请"),
    
    PASSWD_MAX_ERRCNT("2060", "当天密码错误次数过多，请明天再试"),

    REPEAT_LIKE_PRAISE("2061", "重复点赞"),
    MEMBER_LVL_NOT_EXIST("2062", "会员等级不存在"),

    /*
     * 原石、燃力以3开始
     */
    UNSUITABLE_TRADE_NO_EVENT("3000", "事件不适用的交易单号生成场景"),

    WRONG_STATUS_TRANSITION("3001", "错误的状态迁移终态"),

    COIN_BALANCE_BUZU("3002", "原石余额不足"),

    /*
     * 活动相关 4000 - 4999
     */
    CAMPAIGN_NOT_EXSIT("4000", "活动不存在"),
    CROWFUNDING_NOT_EXSIT("4001", "众筹活动不存在"),
    CROWFUNDING_APPLY_FAIL("4002", "众筹参与失败"),
    QUERY_CROWFUNDING_ORDER_FAIL("4003", "查询众筹订单失败"),
    QUERY_PRIZE_ISSUE_FAIL("4004", "查询用户奖品失败"),
    VOTE_APPLY_ERROR("4005", "投票申请错误"),


    /*
     * 兑换相关 5000 - 5999
     */
    EXCHANGE_GOODS_NOT_EXIST("5000", "兑换产品不存在或己下架"),
    EXCHANGE_CODE_NOT_ENOUGH("5001", "此兑换产品的库存不足"),


    /*
     * 辅助工具类操作，以8开始
     */
    DECODE_FILE_BASE64_ERROR("8000", "解析BASE64文件错误"),

    PARSE_DATE_ERROR("8010", "日期解析错误"),

    OSS_DOWNLOAD_ERROR("8020", "OSS文件下载异常"),

    ;

    private String code;

    private String message;
}
