package com.kakxix.base.common.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P>
 * 健康检查
 * <P>
 */
@RestController
public class HealthController {

    /**
     * securitys
     *
     * @return
     */
    @RequestMapping(value = "/api/securitys/hello", method = RequestMethod.GET)
    public String securitys() {
        return "securitys hello";
    }

    /**
     * ok
     *
     * @return
     */
    @RequestMapping(value = "/ok")
    public String ok() {
        return "ok";
    }

    /**
     * securitysOk
     *
     * @return
     */
    @RequestMapping(value = "/securitysOk")
    public String securitysOk() {
        return "securitysOk";
    }

    /**
     * health
     *
     * @return
     */
    @RequestMapping(value = "/health")
    public String health() {
        return "securitys health";
    }

    /**
     * apiHealth
     *
     * @return
     */
    @RequestMapping(value = "/api/health")
    public String apiHealth() {
        return "api health";
    }

}
